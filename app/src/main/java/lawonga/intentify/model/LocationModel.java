package lawonga.intentify.model;

/**
 * Created by Andy on 9/23/2016.
 */
public class LocationModel {
    double lat, lon;

    public LocationModel(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
