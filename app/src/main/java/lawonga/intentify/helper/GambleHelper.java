package lawonga.intentify.helper;

/**
 * Created by Andy on 9/23/2016.
 */
public class GambleHelper {
    private static GambleHelper ourInstance = new GambleHelper();

    public static GambleHelper getInstance() {
        return ourInstance;
    }

    private GambleHelper() {
    }


}
