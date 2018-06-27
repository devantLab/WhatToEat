package pl.devant.whattoeat.model;

/**
 * Created by thomas on 26.06.18.
 */

public class Day {

    private String from;
    private String to;

    public Day(){};

    public Day(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Open hours{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
