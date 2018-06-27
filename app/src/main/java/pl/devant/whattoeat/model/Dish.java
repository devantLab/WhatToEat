package pl.devant.whattoeat.model;

import java.util.Map;

/**
 * Created by thomas on 24.06.18.
 */

public class Dish extends Restaurant {

    private String name;
    private String ingredients;
    private String value;
    private String mark;
    private String price;
    private int clicks;

    public Dish(){};

    public Dish(Dish dish, Restaurant restaurant) {

        super(restaurant.getName(), restaurant.getDescription(), restaurant.getCity(),
                restaurant.getStreet(),restaurant.getCoordinates(),restaurant.getImages(),
                restaurant.getDishes(), restaurant.getOpenHours());
        this.name = dish.getName();
        this.ingredients = dish.getIngredients();
        this.value = dish.getValue();
        this.mark = dish.getMark();
        this.price = dish.getPrice();
        this.clicks = dish.getClicks();

    }

    public String getName() {
        return name;
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
                "name='" + name + '\'' +
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
        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        if (ingredients != null ? !ingredients.equals(dish.ingredients) : dish.ingredients != null)
            return false;
        if (value != null ? !value.equals(dish.value) : dish.value != null) return false;
        if (mark != null ? !mark.equals(dish.mark) : dish.mark != null) return false;
        return price != null ? price.equals(dish.price) : dish.price == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + clicks;
        return result;
    }
}
