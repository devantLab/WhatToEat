package pl.devant.whattoeat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import java.util.ArrayList;

import pl.devant.whattoeat.R;
import pl.devant.whattoeat.model.Statemets;
import pl.devant.whattoeat.model.data.Dish;
import pl.devant.whattoeat.model.data.Restaurant;
import pl.devant.whattoeat.presenters.DishDescriptionActivity;
import pl.devant.whattoeat.presenters.DishesListActivity;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private Button randomDishChangeButton;
    private SearchView searchView;
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<Dish> dishes = new ArrayList<>();


    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        randomDishChangeButton = view.findViewById(R.id.randomDishChangeButton);
        searchView = view.findViewById(R.id.homeSearchView);


        randomDishChangeButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DishDescriptionActivity.class);
            startActivity(intent);
        });

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                Log.d(TAG, "onQueryTextSubmit: "+query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        getData();
        return view;
    }


    private void getData(){
        Bundle bundle = getArguments();
        restaurants = bundle.getParcelableArrayList(Statemets.BUNDLE_RESTARANTS);
        dishes = bundle.getParcelableArrayList(Statemets.BUNDLE_DISHES);
        Log.d(TAG, "getData: " + restaurants);
        Log.d(TAG, "getData: " + dishes);
    }

    private void search(String searchDish){
        Intent intent = new Intent(getContext(), DishesListActivity.class);
        for(int i = 0; i< dishes.size(); i++)
        {
            if(dishes.get(i).getDishName().contains(searchDish))
            {
                intent.putExtra("matchDishes", dishes);
                startActivity(intent);
            }
        }
    }
}
