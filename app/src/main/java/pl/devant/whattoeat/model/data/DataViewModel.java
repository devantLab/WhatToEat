package pl.devant.whattoeat.model.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by thomas on 26.06.18.
 */

public class DataViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Restaurant>> restaurants = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Dish>> dishes = new MutableLiveData<>();

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants.setValue(restaurants);
        Log.wtf("Data", restaurants.toString());
    }
    public void setDishes(ArrayList<Dish> dishes)
    {
        this.dishes.setValue(dishes);
    }
    public LiveData<ArrayList<Restaurant>> getRestaurants() {
        if (restaurants == null) {
            restaurants = new MutableLiveData<>();
        }
        return restaurants;
    }
    public LiveData<ArrayList<Dish>> getDishes() {
        if (dishes == null) {
            dishes = new MutableLiveData<>();
        }
        return dishes;
    }
}
