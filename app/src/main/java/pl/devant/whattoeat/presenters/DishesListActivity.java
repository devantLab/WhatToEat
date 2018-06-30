package pl.devant.whattoeat.presenters;

import android.content.Intent;
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

    private DishAdapter dishAdapter;
    private RecyclerView recyclerView;

    private ArrayList<Dish> dishes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_list);

        dishes = (ArrayList<Dish>) getIntent().getExtras().get("matchDishes");

        Log.d(TAG, "onCreate: "+dishes.toString());
        recyclerView = findViewById(R.id.dishRecyclerView);

        dishAdapter = new DishAdapter(dishes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dishAdapter);

        getDishesData();

//        Log.wtf(TAG, "dish: "+dishes.toString());
//        Log.wtf(TAG, "res: "+restaurants.toString());

    }

    private void getDishesData(){

    }

}
