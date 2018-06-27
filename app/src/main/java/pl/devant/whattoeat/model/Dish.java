package pl.devant.whattoeat.model;

/**
 * Created by thomas on 24.06.18.
 */

public class Dish extends Restarant {

    private String name;
    private String ingredients;
    private String value;
    private double mark;
    private double price;
    private int clicks;


    public Dish(String name, String ingredients, String value, String mark, String price, int clicks) {
        this.name = name;
        this.ingredients = ingredients;
        this.value = value;
        this.mark = (mark != null) ? Double.parseDouble(mark) : 0.0;
        this.price = (price != null) ? Double.parseDouble(price) : 0.0;
        this.clicks = clicks;
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

    public double getMark() {
        return mark;
    }

    public double getPrice() {
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

        Dish dish = (Dish) o;

        if (Double.compare(dish.mark, mark) != 0) return false;
        if (Double.compare(dish.price, price) != 0) return false;
        if (clicks != dish.clicks) return false;
        if (!name.equals(dish.name)) return false;
        if (!ingredients.equals(dish.ingredients)) return false;
        return value.equals(dish.value);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + ingredients.hashCode();
        result = 31 * result + value.hashCode();
        temp = Double.doubleToLongBits(mark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + clicks;
        return result;
    }
}
