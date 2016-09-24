package lawonga.intentify;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import lawonga.intentify.model.LocationModel;

/**
 * Created by Andy on 9/23/2016.
 */
public class LocationReporter {
    private static LocationReporter locationReporter = new LocationReporter();
    private LocationModel lastReportedLocation = null;
    private LocationManager locationManager;
    private Context context;

    public static LocationReporter getInstance() {
        return locationReporter;
    }

    public LocationReporter initInstance(Context context) {
        this.context = context;
        return locationReporter;
    }

    public void startListeningForUpdates() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,
                1, locationListener);
    }

    private LocationReporter() {
    }

    /**
     * @return can be null!
     */
    public LocationModel outputLocation() {
        return lastReportedLocation;
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            lastReportedLocation.setLat(location.getLatitude());
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

}
