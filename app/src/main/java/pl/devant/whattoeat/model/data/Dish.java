package pl.devant.whattoeat.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by thomas on 24.06.18.
 */

public class Dish extends Restaurant implements Parcelable {

    private String dishName;
    private String ingredients;
    private String value;
    private String mark;
    private String price;
    private int clicks;
    private ArrayList<Dish> dish;

    public Dish(){}

    public Dish(Dish dish, Restaurant restaurant) {

        super(restaurant.getRestName(), restaurant.getDescription(), restaurant.getCity(),
                restaurant.getStreet(),restaurant.getCoordinates(),restaurant.getImages(),
                restaurant.getDishes(), restaurant.getOpenHours());
        this.dishName = dish.getDishName();
        this.ingredients = dish.getIngredients();
        this.value = dish.getValue();
        this.mark = dish.getMark();
        this.price = dish.getPrice();
        this.clicks = dish.getClicks();

    }

    public String getDishName() {
        return dishName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getValue() {
        return value;
    }

    public String getMark() {
        return mark;
    }

    public String getPrice() {
        return price;
    }

    public int getClicks() {
        return clicks;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishName='" + dishName + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", value='" + value + '\'' +
                ", mark=" + mark +
                ", price=" + price +
                ", clicks=" + clicks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Dish dish = (Dish) o;

        if (clicks != dish.clicks) return false;
        if (dishName != null ? !dishName.equals(dish.dishName) : dish.dishName != null) return false;
        if (ingredients != null ? !ingredients.equals(dish.ingredients) : dish.ingredients != null)
            return false;
        if (value != null ? !value.equals(dish.value) : dish.value != null) return false;
        if (mark != null ? !mark.equals(dish.mark) : dish.mark != null) return false;
        return price != null ? price.equals(dish.price) : dish.price == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dishName != null ? dishName.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + clicks;
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dishName);
        dest.writeString(value);
        dest.writeString(price);
    }


    public Dish(Parcel in)
    {
        dishName = in.readString();
        value = in.readString();
        price = in.readString();
    }

    public static final Parcelable.Creator<Dish> CREATOR = new Parcelable.Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel source) {
            return new Dish(source);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

}
