package pl.devant.whattoeat.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by thomas on 26.06.18.
 */

public class DataViewModel extends ViewModel {

    private MutableLiveData<List<Restaurant>> restaurants;
    private MutableLiveData<List<Dish>> dishes;

    public MutableLiveData<List<Restaurant>> getRestaurants() {
        if (restaurants == null) {
            restaurants = new MutableLiveData<>();
        }
        return restaurants;
    }
    public MutableLiveData<List<Dish>> getDishes() {
        if (dishes == null) {
            dishes = new MutableLiveData<>();
        }
        return dishes;
    }
}
