package lawonga.intentify.helper;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import lawonga.intentify.keys.KeyLevel1;
import lawonga.intentify.keys.KeyLevel2;

/**
 * Created by Andy on 9/23/2016.
 * Helper only for firebase
 */
public class FirebaseHelper<E> {
    private static FirebaseHelper ourInstance = new FirebaseHelper();
    private static FirebaseDatabase database;
    private static String TAG = "FirebaseHelper";
    private KeyLevel1 keyLevel1;
    private KeyLevel2 keyLevel2;

    public static FirebaseHelper getInstance() {
        database = FirebaseDatabase.getInstance();
        return ourInstance;
    }

    private FirebaseHelper() {
    }

    public static FirebaseDatabase getDatabase() {
        return database;
    }

    public void setKeyLevel1(KeyLevel1 keyLevel1) {
        this.keyLevel1 = keyLevel1;
    }

    public void write(KeyLevel1 keyLevel1, KeyLevel2 keyLevel2, E element) {
        DatabaseReference reference = database.getReference(keyLevel1.name()).child(keyLevel2.name());
        reference.setValue(element);
    }

    public void write(KeyLevel2 keyLevel2, E element){
        DatabaseReference reference = database.getReference(keyLevel1.name()).child(keyLevel2.name());
        reference.setValue(element);
    }

    public void read(KeyLevel1 keyLevel1, KeyLevel2 keyLevel2) {
        DatabaseReference reference = database.getReference(keyLevel1.name()).child(keyLevel2.name());

        // Read from the database
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
