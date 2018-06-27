package pl.devant.whattoeat.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import pl.devant.whattoeat.R;
import pl.devant.whattoeat.model.data.Dish;
import pl.devant.whattoeat.model.data.Restaurant;
import pl.devant.whattoeat.presenters.DishesListActivity;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";


    private Button randomDishChangeButton;
    private SearchView searchView;


    private ArrayList<Restaurant> restaurant = new ArrayList<>();
    private ArrayList<Dish> dishes = new ArrayList<>();


    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        randomDishChangeButton = view.findViewById(R.id.randomDishChangeButton);
        searchView = view.findViewById(R.id.homeSearchView);

        randomDishChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DishesListActivity.class);
                startActivity(intent);
            }
        });

//        search(searchView.getQuery().toString());

        getDishesData();
        return view;
    }




    private void getDishesData(){


    }

//    private void search(String searchDish){
//
//        Intent intent = new Intent(getContext(), DishesListActivity.class);
//        intent.putExtra("matchDishes", matchDishes);
//        startActivity(intent);
//    }
}
