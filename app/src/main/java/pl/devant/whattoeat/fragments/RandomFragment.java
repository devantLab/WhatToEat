package pl.devant.whattoeat.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pl.devant.whattoeat.R;
import pl.devant.whattoeat.model.data.Dish;
import pl.devant.whattoeat.model.data.Restaurant;


public class RandomFragment extends Fragment {

    private static final String TAG = "RandomFragment";

    private SharedPreferences mPrefs;
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<Dish> dishes = new ArrayList<>();

    public RandomFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        getDishesData();

        return view;
    }

    private void getDishesData(){
        Type listType = new TypeToken<List<Restaurant>>(){}.getType();
        Gson gson = new Gson();
        String json = mPrefs.getString("restaurants","");

        restaurants = gson.fromJson(json, listType);

        for(int i = 0; i<restaurants.size(); i ++)
        {
            dishes = (ArrayList<Dish>) restaurants.get(i).getDishes();
        }

        Log.wtf(TAG+": getData: ", restaurants.toString());
        Log.wtf(TAG+": getData: ", dishes.toString());
    }
}
