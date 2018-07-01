package pl.devant.whattoeat.model.adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pl.devant.whattoeat.R;
import pl.devant.whattoeat.model.data.Day;
import pl.devant.whattoeat.model.data.Dish;
import pl.devant.whattoeat.model.data.Restaurant;

/**
 * Created by goracy on 27.06.18.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {


    private ArrayList<Restaurant> restaurant = new ArrayList<>();

    public RestaurantAdapter(ArrayList<Restaurant> restaurant){this.restaurant = restaurant;}

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_restaurant_description, parent, false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.bindRestaurant(restaurant.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurant.size();
    }


    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView restaurantName;
        private TextView restaurantAdress;
        private TextView monFrom;
        private TextView monTo;
        private TextView tueFrom;
        private TextView tueTo;
        private TextView wenFrom;
        private TextView wenTo;
        private TextView thuFrom;
        private TextView thuTo;
        private TextView friFrom;
        private TextView friTo;
        private TextView satFrom;
        private TextView satTo;
        private TextView sunFrom;
        private TextView sunTo;



        public RestaurantViewHolder(View itemView) {
            super(itemView);

            restaurantName  = itemView.findViewById(R.id.restaurantDescriptionRestaurantNameTextView);
            restaurantAdress = itemView.findViewById(R.id.restaurantDescriptionRestaurantAdressTextView);
            monFrom = itemView.findViewById(R.id.mondayFrom);
            monTo = itemView.findViewById(R.id.mondayTo);
            tueFrom = itemView.findViewById(R.id.tuesdayFrom);
            tueTo = itemView.findViewById(R.id.tuesdayTo);
            wenFrom = itemView.findViewById(R.id.wednesdayFrom);
            wenTo = itemView.findViewById(R.id.wednesdayTo);
            thuFrom = itemView.findViewById(R.id.thursdayFrom);
            thuTo = itemView.findViewById(R.id.thursdayTo);
            friFrom = itemView.findViewById(R.id.fridayFrom);
            friTo = itemView.findViewById(R.id.fridayTo);
            sunFrom = itemView.findViewById(R.id.saturdayFrom);
            sunTo = itemView.findViewById(R.id.saturdayTo);
            satFrom = itemView.findViewById(R.id.sundayFrom);
            satTo = itemView.findViewById(R.id.sundayTo);


            itemView.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        private void bindRestaurant(Restaurant restaurant){
            restaurantName.setText(restaurant.getRestName());
            restaurantAdress.setText(restaurant.getStreet()+", "+restaurant.getCity());

        }

        @Override
        public void onClick(View v) {

        }
        public void startDishDescription(){

        }
    }
}
