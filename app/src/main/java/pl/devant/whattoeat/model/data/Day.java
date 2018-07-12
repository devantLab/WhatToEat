package pl.devant.whattoeat.model.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thomas on 26.06.18.
 */

public class Day implements Parcelable {

    private String from;
    private String to;

    public Day(){};

    public Day(String from, String to) {
        this.from = from;
        this.to = to;
    }

    protected Day(Parcel in) {
        from = in.readString();
        to = in.readString();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(from);
        dest.writeString(to);
    }
}
