package pl.devant.whattoeat.model;

import java.util.List;
import java.util.Map;

/**
 * Created by thomas on 24.06.18.
// */

public class Restarant {

    private String name;
    private String description;
    private String city;
    private String street;
    private Coordinates coordinates;
    private List<String> images;
    private List<Dish> dishes;
    private Map<String, Day> openHours;

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

    public Coordinates getCoordinates() {
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

        Restarant restarant = (Restarant) o;

        if (name != null ? !name.equals(restarant.name) : restarant.name != null) return false;
        if (description != null ? !description.equals(restarant.description) : restarant.description != null)
            return false;
        if (city != null ? !city.equals(restarant.city) : restarant.city != null) return false;
        if (street != null ? !street.equals(restarant.street) : restarant.street != null)
            return false;
        if (coordinates != null ? !coordinates.equals(restarant.coordinates) : restarant.coordinates != null)
            return false;
        if (images != null ? !images.equals(restarant.images) : restarant.images != null)
            return false;
        if (dishes != null ? !dishes.equals(restarant.dishes) : restarant.dishes != null)
            return false;
        return openHours != null ? openHours.equals(restarant.openHours) : restarant.openHours == null;
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
        return "Restarant{" +
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
