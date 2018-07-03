package pl.devant.whattoeat.fragments;

import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.devant.whattoeat.R;
import pl.devant.whattoeat.model.Statemets;
import pl.devant.whattoeat.model.adapters.SearchSuggestionsAdapter;
import pl.devant.whattoeat.model.data.Dish;
import pl.devant.whattoeat.model.data.Restaurant;
import pl.devant.whattoeat.presenters.DishDescriptionActivity;
import pl.devant.whattoeat.presenters.DishesListActivity;


public class HomeFragment extends Fragment {
    //Debug
    private static final String TAG = "HomeFragment";
    //UI components
    @BindView(R.id.randomDishChangeButton) Button randomDishChangeButton;
    @BindView(R.id.searchBar) MaterialSearchBar searchBar;
    //Variables
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<Dish> dishes = new ArrayList<>();
    private Unbinder unbinder;
    private SearchSuggestionsAdapter searchSuggestionsAdapter;
    public HomeFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        searchSuggestionsAdapter = new SearchSuggestionsAdapter(inflater);
        searchBar.setMaxSuggestionCount(2);
        searchBar.setHint("Wyszukaj danie...");
        searchBar.setSpeechMode(false);
        searchSuggestionsAdapter.setSuggestions(dishes);
        searchBar.setCustomSuggestionAdapter(searchSuggestionsAdapter);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + searchBar.getText());
                // send the entered text to our filter and let it manage everything
                searchSuggestionsAdapter.getFilter().filter(searchBar.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });


        randomDishChangeButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DishDescriptionActivity.class);
            startActivity(intent);
        });
        
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
