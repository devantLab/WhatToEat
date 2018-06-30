package pl.devant.whattoeat.presenters;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pl.devant.whattoeat.R;
import pl.devant.whattoeat.model.data.Dish;
import pl.devant.whattoeat.model.data.Restaurant;

public class SplashScreen extends AppCompatActivity {

    private static final String TAG = "SplashScreen";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference().child("restaurants");
    private DatabaseReference myRefCount = database.getReference().child("restaurantsCount");
    private SharedPreferences mPrefs;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private boolean mLocationPermissionsGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private int restaurantsCount;
    private ArrayList<Dish> dishes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        databaseGetCount();
        databaseConnection();

    }

    private void databaseGetCount(){
        myRefCount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                restaurantsCount = dataSnapshot.getValue(Integer.class);
                Log.wtf(TAG, restaurantsCount+"");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void databaseConnection(){
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.wtf(TAG, dataSnapshot.toString());
                Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                Log.d(TAG, "onDataChange: "+ restaurant.toString());
                for (int i = 0; i < restaurant.getDishes().size(); i++){
                    Dish dish = new Dish(restaurant.getDishes().get(i), restaurant);
//                    Toast.makeText(SplashScreen.this, ""+ dish.getCoordinates(), Toast.LENGTH_SHORT).show();

                    dishes.add(dish);
                }

                restaurants.add(restaurant);

                if(restaurants.size()==restaurantsCount)
                {
                    setDataToSharedPreferences();
                    startMainActivity();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setDataToSharedPreferences(){
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Type restaurantListType = new TypeToken<List<Restaurant>>(){}.getType();
        Type dishListType = new TypeToken<List<Dish>>(){}.getType();
        Gson gson = new Gson();
        String restaurantJson = gson.toJson(restaurants, restaurantListType);
        String dishJson = gson.toJson(dishes, dishListType);
        prefsEditor.putString("restaurants", restaurantJson);
        prefsEditor.putString("dishes", dishJson);
        prefsEditor.apply();
    }
}
