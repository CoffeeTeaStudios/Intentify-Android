package lawonga.intentify.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

import lawonga.intentify.R;
import lawonga.intentify.helper.LocationHelper;
import lawonga.intentify.helper.NotificationHelper;
import lawonga.intentify.helper.PreferencesHelper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Andy on 9/23/2016.
 */
public class BaseApplication extends Application implements BootstrapNotifier {
    private BeaconManager beaconManager; // bad practice if anyone is looking at this code don't actually do this in production
    private RegionBootstrap regionBootstrap;
    /**
     * Oncreate init
     */
    @Override
    public void onCreate() {
        super.onCreate();
        // Calligraphy
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        NotificationHelper.getInstance().initInstance(getApplicationContext());
        LocationHelper.getInstance().initInstance(getApplicationContext());
        PreferencesHelper.getInstance().initInstance(getApplicationContext());

        beaconManager = BeaconManager.getInstanceForApplication(this);
        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

        Region region = new Region("com.example.myapp.boostrapRegion", null, null, null);
        regionBootstrap = new RegionBootstrap(this, region);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }

    @Override
    public void didEnterRegion(Region region) {
        // Pull data from firebase and trigger a notification


    }

    @Override
    public void didExitRegion(Region region) {

    }

    @Override
    public void didDetermineStateForRegion(int i, Region region) {

    }
}
