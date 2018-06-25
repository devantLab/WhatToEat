package pl.devant.whattoeat.model;

import java.util.List;

/**
 * Created by thomas on 24.06.18.
// */
//{
//        "restaruants" : [ {
//
//        "coordinates" : {
//        "latitude" : "54.351736",
//        "longitude" : "18.657847"
//        },
//
//        "images" : [],
//
//        "openHours" : {
//        "friday" : {
//        "from" : "10:00",
//        "to" : "00:00"
//        }
//        },
//
//        } ]
//        }
public class Restarant {

    private String name;
    private String description;
    private String city;
    private String street;
    private Coordinates coordinates;
    private List<String> images;
    private List<Dish> dishes;
    //TODO: ROZKMINIC JAK TO MA FUNKCJONOWAC Z TYMI GODZINAMI OTWARCIA XD
}
