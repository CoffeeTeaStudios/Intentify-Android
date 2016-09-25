package lawonga.intentify.modelfirebase;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Andy on 9/24/2016.
 */
@IgnoreExtraProperties
public class TokenModel {
    String uniqueIdentifier;
    String user;

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
