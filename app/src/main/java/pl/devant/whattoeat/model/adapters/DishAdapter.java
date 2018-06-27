package pl.devant.whattoeat.model.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pl.devant.whattoeat.R;
import pl.devant.whattoeat.model.data.Dish;

/**
 * Created by goracy on 27.06.18.
 */

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {


    private ArrayList<Dish> dish = new ArrayList<>();

    public DishAdapter(ArrayList<Dish> dish){this.dish = dish;}

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).
               inflate(R.layout.activity_dishes_list, parent, false);
       DishViewHolder viewHolder = new DishViewHolder(view);


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


        public DishViewHolder(View itemView) {
            super(itemView);

            dishName = itemView.findViewById(R.id.dishName);
            value = itemView.findViewById(R.id.dishGramature);
            price = itemView.findViewById(R.id.dishPrice);
            itemView.setOnClickListener(this);
        }

        private void bindDish(Dish dish){
            dishName.setText(dish.getDishName());
            value.setText(dish.getValue());
            price.setText(dish.getPrice());
        }

        @Override
        public void onClick(View v) {

        }
        public void startDishDescription(){

        }
    }
}
