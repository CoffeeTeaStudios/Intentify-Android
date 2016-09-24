package lawonga.intentify.helper;

import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import lawonga.intentify.keys.KeyLevel1;
import lawonga.intentify.keys.KeyLevel2;
import lawonga.intentify.model.LocationModel;

/**
 * Created by Andy on 9/23/2016.
 */
public class LocationHelper {

    private static LocationHelper locationHelper = new LocationHelper();
    private LocationModel lastReportedLocation = null;
    private LocationManager locationManager;
    private Context context;

    public static LocationHelper getInstance() {
        return locationHelper;
    }

    public LocationHelper initInstance(Context context) {
        this.context = context;
        return locationHelper;
    }

    public void startListeningForUpdates() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

    private LocationHelper() {
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
            // Set location locally
            lastReportedLocation.setLat(location.getLatitude());
            lastReportedLocation.setLon(location.getLongitude());

            // Send it to firebase too!
            FirebaseHelper firebaseHelper = FirebaseHelper.getInstance();
            firebaseHelper.setKeyLevel1(KeyLevel1.USERS);
            firebaseHelper.write(KeyLevel2.LAT, location.getLatitude());
            firebaseHelper.write(KeyLevel2.LONG, location.getLongitude());
            firebaseHelper.write(KeyLevel2.TIME, Calendar.getInstance().getTimeInMillis());
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
