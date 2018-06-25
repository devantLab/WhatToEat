package pl.devant.whattoeat.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


import java.text.DecimalFormat;
import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;
import butterknife.Unbinder;
import pl.devant.whattoeat.R;


public class MapFragment extends Fragment implements OnMapReadyCallback {
    //debug
    private static final String TAG = "MapFragment";

    //Map configuration components
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private GoogleMap mMap;
    private Circle circle;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Unbinder unbinder;
    //UI components
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton fab;

    private SeekBar rangeSeekBar;
    private SeekBar dishPriceSeekBar;
    private TextView dishPriceTextView;
    private TextView rangeTextView;

    //finals
    private static final float DEFAULT_ZOOM = 14f;
    private static final float DEFAULT_CIRCLE_RADIUS = 200f;
    private static final float DISH_PRICE_START = 1.00f;
    private static final float DISH_PRICE_END = 300.00f;

    public MapFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        fabClick();
        Log.d(TAG, "onCreateView: Current circle range is " + DEFAULT_CIRCLE_RADIUS);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        getDeviceLocation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        try {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);
            mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(),R.raw.orange_maps));
            mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    if(circle != null) {
                        getDeviceLocation((float) circle.getRadius(), true);
                    }
                    return false;
                }
            });


        } catch (Resources.NotFoundException e) {
            Log.d(TAG, "onMapReady: " + e.getMessage());
        }
        getDeviceLocation();


        Toast.makeText(getContext(), "Map is ready! Yupi!", Toast.LENGTH_SHORT).show();
    }
    private float getZoomLevel(Circle circle) {
        float zoomLevel = DEFAULT_ZOOM;
        if (circle != null){
            double radius = circle.getRadius();
            double scale = radius / 200;
            zoomLevel = (float) (16 - Math.log(scale) / Math.log(2));
        }
        return zoomLevel;
    }
    private void getDeviceLocation(final float radius, final boolean animate) {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener((Activity) getContext(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null) {
                    circle = drawCircle(location, radius);
                    if (animate == false)
                        moveCamera(new LatLng(location.getLatitude(), location.getLongitude()), getZoomLevel(circle));
                    else
                        animateCamera(new LatLng(location.getLatitude(), location.getLongitude()), getZoomLevel(circle));
                }
            }
        });

    }
    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener((Activity) getContext(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    circle = drawCircle(location, DEFAULT_CIRCLE_RADIUS);

                    animateCamera(new LatLng(location.getLatitude(), location.getLongitude()), getZoomLevel(circle));
                }
            }
        });

    }

    private void moveCamera(LatLng latLng, float zoom){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
        Log.d(TAG, "moveCamera: " + latLng.toString());
    }
    private void animateCamera(LatLng latLng, float zoom){
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
        Log.d(TAG, "animateCamera: " + latLng.toString());
    }

    private Circle drawCircle(Location location, float radius){
        if(circle != null) circle.remove();
            circle = mMap.addCircle(new CircleOptions()
                    .center(new LatLng(location.getLatitude(), location.getLongitude()))
                    .radius(radius)
                    .strokeColor(Color.parseColor("#00ff00"))
                    .fillColor(0x2600ff00));
            return circle;
    }
    private void fabClick(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                view = getLayoutInflater().from(getContext()).inflate(R.layout.map_fab_dialog, null, false);
                builder.setView(view);
                builder.setPositiveButton("Akceptuj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDeviceLocation(rangeSeekBar.getProgress(), false);
                        Log.d(TAG, "onClick: Akceptuj clicked");
                    }
                });
                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: Anuluj clicked");
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.show();

                dishPriceSeekBar = view.findViewById(R.id.priceSeekBar);
                rangeSeekBar = view.findViewById(R.id.rangeSeekBar);
                rangeTextView = view.findViewById(R.id.rangeTextView);
                dishPriceTextView = view.findViewById(R.id.dishPriceTextView);
                double radius = (circle != null) ? circle.getRadius() : 0;

                rangeTextView.setText((radius < 1000.0) ?(radius + " m") : (decimalFormat.format(radius/1000.0) + " km"));
                onSeekBarsChanged();
            }
        });
    }

    @SuppressLint("NewApi")
    private void onSeekBarsChanged(){
        rangeSeekBar.setMax(5000);
        rangeSeekBar.setMin(200);
        double radius = (circle != null) ? circle.getRadius() : 0;
        rangeSeekBar.setProgress((int) radius);
        rangeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getDeviceLocation(progress, false);
                Log.d(TAG, "onProgressChanged: radius " + progress);
                rangeTextView.setText((progress < 1000.0) ?(progress + " m") : (decimalFormat.format(progress/1000.0) + " km"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dishPriceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {



            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                dishPriceEnd = progresValue;
                dishPriceTextView.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
