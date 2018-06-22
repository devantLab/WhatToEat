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
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.android.gms.tasks.OnSuccessListener;


import pl.devant.whattoeat.R;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private FloatingActionButton fab;

    private MapView mapView;
    private GoogleMap mMap;

    private OnFragmentInteractionListener mListener;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final float DEFAULT_ZOOM = 14f;
    private float rangeCircle = 1000f;
    private int progresRangeValue;
    private float dishPriceStart = 1.00f;
    private float dishPriceEnd = 300.00f;
    private SeekBar rangeSeekBar;
    private SeekBar dishPriceSeekBar;
    private TextView dishPriceTextView;
    private TextView rangeTextView;


    public MapFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_map, container, false);
        // Inflate the layout for this fragment

        fab = view.findViewById(R.id.floatingActionButton);

        mapView = view.findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        fabClick();
        Log.wtf("currentRange", rangeCircle+"");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.wtf("currentRangeRESUME", rangeCircle+"");

        getDeviceLocation();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        mMap = googleMap;
        try {
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(),R.raw.orange_maps));
        } catch (Resources.NotFoundException e) {}
        getDeviceLocation();
        Toast.makeText(getContext(), "Map is ready! Yupi!", Toast.LENGTH_SHORT).show();
    }

    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener((Activity) getContext(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                moveCamera(new LatLng(location.getLatitude(), location.getLongitude()),DEFAULT_ZOOM);
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.setMyLocationEnabled(true);

                Circle circle = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(location.getLatitude(), location.getLongitude()))
                        .radius(rangeCircle)
                        .strokeColor(Color.RED).fillColor(Color.alpha(Color.BLUE)));
            }
        });
    }

    private void moveCamera(LatLng latLng, float zoom){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
    }

    private void fabClick(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                v = getLayoutInflater().inflate(R.layout.map_fab_dialog, null);

                builder.setView(v).setPositiveButton("Akceptuj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rangeCircle = (float) progresRangeValue;
                        getDeviceLocation();
                    }
                }).setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.show();
                dishPriceSeekBar = v.findViewById(R.id.priceSeekBar);
                rangeSeekBar = v.findViewById(R.id.rangeSeekBar);
                rangeTextView = v.findViewById(R.id.rangeTextView);
                dishPriceTextView = v.findViewById(R.id.dishPriceTextView);
                rangeTextView.setText(rangeCircle/1000+" km");
                onSeekBarsChanged();
            }
        });
    }

    @SuppressLint("NewApi")
    private void onSeekBarsChanged(){
        rangeSeekBar.setMax(5000);
        rangeSeekBar.setMin(1000);
        rangeSeekBar.setProgress((int) rangeCircle);
        rangeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresRangeValue = progress;
                rangeCircle = ((float) progresRangeValue);
                Log.wtf("RANGE", String.valueOf(rangeCircle));
                rangeTextView.setText(progresRangeValue/1000+" km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dishPriceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progresValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresValue = progress;
//                dishPriceEnd = progresValue;
                dishPriceTextView.setText(progresValue+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
