package lawonga.intentify.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import lawonga.intentify.keys.KeyLevel3;
import lawonga.intentify.keys.PrefKey;

/**
 * Created by Andy on 9/23/2016.
 */
public class PreferencesHelper {
    private static PreferencesHelper ourInstance = new PreferencesHelper();
    private SharedPreferences sharedPref;

    public PreferencesHelper initInstance(Context context) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(PrefKey.USER_ID.name(), 1);
        editor.apply();
        return ourInstance;
    }

    public static PreferencesHelper getInstance() {
        return ourInstance;
    }

    private PreferencesHelper() {
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(KeyLevel3.uniqueIdentifier.name(), uniqueIdentifier);
        editor.apply();
    }

    public String getUniqueIdentifier(){
        return sharedPref.getString(KeyLevel3.uniqueIdentifier.name(), "");
    }
}
