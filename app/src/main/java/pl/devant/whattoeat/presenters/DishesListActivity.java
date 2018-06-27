package pl.devant.whattoeat.presenters;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pl.devant.whattoeat.R;
import pl.devant.whattoeat.model.adapters.DishAdapter;
import pl.devant.whattoeat.model.data.Dish;
import pl.devant.whattoeat.model.data.Restaurant;

public class DishesListActivity extends AppCompatActivity {

    private static final String TAG = "DishesListFragment";

    private SharedPreferences mPrefs;

    private DishAdapter dishAdapter;
    private RecyclerView recyclerView;

    private ArrayList<Restaurant> restaurant = new ArrayList<>();
    private ArrayList<Dish> dish = new ArrayList<>();
    private ArrayList<Dish> dishes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_list);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        recyclerView = findViewById(R.id.dishRecyclerView);
        dishAdapter = new DishAdapter(dish);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dishAdapter);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);


        getDishesData();

        Log.wtf(TAG, "dish: "+dish.toString());
        Log.wtf(TAG, "dishes: "+dishes.toString());
        Log.wtf(TAG, "res: "+restaurant.toString());

    }

    private void getDishesData(){

    }

}
