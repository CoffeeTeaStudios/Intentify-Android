package lawonga.intentify.helper;

/**
 * Created by Andy on 9/23/2016.
 */
public class BeaconHelper {
    private static BeaconHelper ourInstance = new BeaconHelper();

    public static BeaconHelper getInstance() {
        return ourInstance;
    }

    private BeaconHelper() {
    }
}
