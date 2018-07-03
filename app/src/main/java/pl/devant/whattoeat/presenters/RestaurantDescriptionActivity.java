package pl.devant.whattoeat.presenters;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import pl.devant.whattoeat.R;
import pl.devant.whattoeat.fragments.RestaurantDescriptionInfoFragment;
import pl.devant.whattoeat.fragments.RestaurantDescriptionMenuFragment;
import pl.devant.whattoeat.model.data.Restaurant;

public class RestaurantDescriptionActivity extends AppCompatActivity {
    public static final String TAG="RestaurantDescriptionActivity";

    private Button infoButton;
    private Button menuButton;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_description);

        infoButton = findViewById(R.id.restaurantDescriptionInfoButton);
        menuButton = findViewById(R.id.restaurantDescriptionMenuButton);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        onStartFragmentLoad();
        onButtonClickFragmentLoad();
    }

    private void onStartFragmentLoad(){
        RestaurantDescriptionInfoFragment infoFragment = new RestaurantDescriptionInfoFragment();
        fragmentTransaction.add(R.id.fragment_container, infoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void onButtonClickFragmentLoad(){
        infoButton.setOnClickListener(v -> {
            RestaurantDescriptionInfoFragment infoFragment = new RestaurantDescriptionInfoFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, infoFragment);
//            transaction.addToBackStack(null);
            transaction.commit();
        });
        menuButton.setOnClickListener(v -> {
            RestaurantDescriptionMenuFragment menuFragment = new RestaurantDescriptionMenuFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, menuFragment);
//            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

}