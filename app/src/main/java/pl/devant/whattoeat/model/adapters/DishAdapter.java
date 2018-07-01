package pl.devant.whattoeat.model.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pl.devant.whattoeat.R;
import pl.devant.whattoeat.model.data.Dish;
import pl.devant.whattoeat.presenters.DishDescriptionActivity;

/**
 * Created by goracy on 27.06.18.
 */

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {

    private Context context;
    private ArrayList<Dish> dish = new ArrayList<>();

    public DishAdapter(ArrayList<Dish> dish){this.dish = dish;}

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).
               inflate(R.layout.dish_list_item, parent, false);
       DishViewHolder viewHolder = new DishViewHolder(view);

       context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        holder.bindDish(dish.get(position));
    }

    @Override
    public int getItemCount() {
        return dish.size();
    }


    public class DishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView dishName;
        private TextView value;
        private TextView price;
        private TextView restaurantName;
        private ImageView dishImage;

        public DishViewHolder(View itemView) {
            super(itemView);

            dishName = itemView.findViewById(R.id.dishItemDishName);
            value = itemView.findViewById(R.id.dishItemGramature);
            price = itemView.findViewById(R.id.dishItemPrice);
            restaurantName = itemView.findViewById(R.id.dishItemRestaurantName);
            dishImage = itemView.findViewById(R.id.dishItemImage);
            itemView.setOnClickListener(this);
        }

        private void bindDish(Dish dish){
            dishName.setText(dish.getDishName());
            value.setText(dish.getValue());
            price.setText(dish.getPrice());
            restaurantName.setText(dish.getRestName());
            dishImage.setImageResource(R.drawable.ic_pizza_64dp);
        }

        @Override
        public void onClick(View v) {
            startDishDescription();
        }
        public void startDishDescription(){
            Intent intent = new Intent(context, DishDescriptionActivity.class);
            intent.putExtra("clickedEvent", dish.get(this.getAdapterPosition()));

            Pair[] pairs = new Pair[2];
            pairs[0] = new Pair<View, String>(dishName,"dishItemDishName");
            pairs[1] = new Pair<View, String>(dishImage,"dishItemImage");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pairs);

            context.startActivity(intent, options.toBundle());
        }
    }
}
