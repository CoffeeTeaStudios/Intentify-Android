package lawonga.intentify.modelfirebase;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Andy on 9/24/2016.
 */
@IgnoreExtraProperties
public class UserModel {
    public String firstName, lastName, uniqueIdentifier;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }
}
