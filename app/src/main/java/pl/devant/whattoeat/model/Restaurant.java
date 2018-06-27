package pl.devant.whattoeat.model;

import java.util.List;
import java.util.Map;

/**
 * Created by thomas on 24.06.18.
// */

public class Restaurant {

    private String name;
    private String description;
    private String city;
    private String street;
    private Map<String, String> coordinates;
    private List<String> images;
    private List<Dish> dishes;
    private Map<String, Day> openHours;


    public Restaurant(String name, String description, String city,
                      String street, Map<String, String> coordinates,
                      List<String> images, List<Dish> dishes, Map<String, Day> openHours) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.street = street;
        this.coordinates = coordinates;
        this.images = images;
        this.dishes = dishes;
        this.openHours = openHours;
    }

    public Restaurant(){};

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Map<String, String> getCoordinates() {
        return coordinates;
    }

    public List<String> getImages() {
        return images;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Map<String, Day> getOpenHours() {
        return openHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant restaurant = (Restaurant) o;

        if (name != null ? !name.equals(restaurant.name) : restaurant.name != null) return false;
        if (description != null ? !description.equals(restaurant.description) : restaurant.description != null)
            return false;
        if (city != null ? !city.equals(restaurant.city) : restaurant.city != null) return false;
        if (street != null ? !street.equals(restaurant.street) : restaurant.street != null)
            return false;
        if (coordinates != null ? !coordinates.equals(restaurant.coordinates) : restaurant.coordinates != null)
            return false;
        if (images != null ? !images.equals(restaurant.images) : restaurant.images != null)
            return false;
        if (dishes != null ? !dishes.equals(restaurant.dishes) : restaurant.dishes != null)
            return false;
        return openHours != null ? openHours.equals(restaurant.openHours) : restaurant.openHours == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        result = 31 * result + (openHours != null ? openHours.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", coordinates=" + coordinates +
                ", images=" + images +
                ", dishes=" + dishes +
                ", openHours=" + openHours +
                '}';
    }
}
