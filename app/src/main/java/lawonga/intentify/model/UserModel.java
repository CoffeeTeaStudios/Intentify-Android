package lawonga.intentify.model;

/**
 * Created by Andy on 9/23/2016.
 */
public class UserModel {
    private String name;
    private double lat, lon;
    private long userId;

    public UserModel(String name, double lat, double lon, long userId) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
