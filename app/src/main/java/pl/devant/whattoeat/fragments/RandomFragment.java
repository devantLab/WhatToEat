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

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.devant.whattoeat.R;
import pl.devant.whattoeat.model.Statemets;
import pl.devant.whattoeat.model.data.Dish;
import pl.devant.whattoeat.model.data.Restaurant;


public class RandomFragment extends Fragment {
    //Debug
    private static final String TAG = "RandomFragment";
    //Variables
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<Dish> dishes = new ArrayList<>();
    private Unbinder unbinder;

    public RandomFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_random, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getData() {
        Bundle bundle = getArguments();
        restaurants = bundle.getParcelableArrayList(Statemets.BUNDLE_RESTARANTS);
        dishes = bundle.getParcelableArrayList(Statemets.BUNDLE_DISHES);
        Log.d(TAG, "getData: " + restaurants);
        Log.d(TAG, "getData: " + dishes);
    }
}
