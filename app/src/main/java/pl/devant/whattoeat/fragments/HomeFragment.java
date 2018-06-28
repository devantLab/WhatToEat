package pl.devant.whattoeat.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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
import pl.devant.whattoeat.model.data.DataViewModel;
import pl.devant.whattoeat.model.data.Dish;
import pl.devant.whattoeat.model.data.Restaurant;
import pl.devant.whattoeat.presenters.DishesListActivity;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";


    private Button randomDishChangeButton;
    private SearchView searchView;

    private SharedPreferences mPrefs;
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<Dish> dishes = new ArrayList<>();


    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        randomDishChangeButton = view.findViewById(R.id.randomDishChangeButton);
        searchView = view.findViewById(R.id.homeSearchView);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        randomDishChangeButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DishesListActivity.class);
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

        getDishesData();
        return view;
    }


    private void getDishesData(){
        Type listType = new TypeToken<List<Dish>>(){}.getType();
        Gson gson = new Gson();
        String json = mPrefs.getString("dishes","");

        dishes = gson.fromJson(json, listType);

        Log.wtf(TAG+": getData: ", restaurants.toString());
        Log.wtf(TAG+": getData: ", dishes.toString());
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
