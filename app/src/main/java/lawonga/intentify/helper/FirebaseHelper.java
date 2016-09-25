package lawonga.intentify.helper;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;

import lawonga.intentify.keys.KeyLevel1;
import lawonga.intentify.keys.KeyLevel2;
import lawonga.intentify.keys.KeyLevel3;
import lawonga.intentify.modelfirebase.PoolModel;
import lawonga.intentify.modelfirebase.TokenModel;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    private ArrayList<PoolModel> poolList = new ArrayList<>();
    private String uniqueIdentifier;

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

    public void write(KeyLevel2 keyLevel2, E element) {
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

    public void readFirstToken() {
        DatabaseReference reference = database.getReference(KeyLevel1.tokens.name());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String uniqueIdentifier, user;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.e(TAG, "onDataChange: " + snapshot.child("uniqueIdentifier").getValue());
                    TokenModel tokenModel = snapshot.getValue(TokenModel.class);
                    Log.e(TAG, "onDataChange: " + tokenModel.getUser());
                    break;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void readTokens() {
        // Read from the database
        DatabaseReference reference = database.getReference(KeyLevel1.pools.name());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public void writeTenTokens() {
        Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    writeToken();
                    try {
                        Thread.sleep(200 + new Random().nextInt(400));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void resetTokens() {
        Observable.fromCallable(new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                DatabaseReference reference = database.getReference(KeyLevel1.gambles.name());
                // Write to first one only
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        uniqueIdentifier = null;
                        for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            uniqueIdentifier = (String) snapshot.child(KeyLevel3.pools.name()).child("0").getValue();
                            // Create the pool
                            final DatabaseReference reference = database.getReference(KeyLevel1.pools.name()).child(uniqueIdentifier).child(KeyLevel3.tokens.name());
                            reference.setValue(null);
                            break;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void writeTokenOld() {
        DatabaseReference tokenReference = database.getReference(KeyLevel1.tokens.name());
        final String pushKey = (tokenReference.push()).getKey();
        TokenModel tokenModel = new TokenModel();
        tokenModel.setUniqueIdentifier(pushKey);
        tokenModel.setUser(PreferencesHelper.getInstance().getUniqueIdentifier());
        tokenReference.child(pushKey).setValue(tokenModel);

        DatabaseReference reference = database.getReference(KeyLevel1.gambles.name());
        // Write to first one only
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uniqueIdentifier = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    uniqueIdentifier = (String) snapshot.child(KeyLevel3.pools.name()).child("0").getValue();
                    Log.e(TAG, "onDataChange: " + uniqueIdentifier);
                    // Create the pool
                    final DatabaseReference reference = database.getReference(KeyLevel1.pools.name()).child(uniqueIdentifier).child(KeyLevel3.tokens.name());
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int key = 0;
                            ArrayList<String> string = new ArrayList<String>();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                string.add(snapshot.getKey());
                            }
                            key = Integer.parseInt(string.get(string.size() - 1));
                            reference.child(String.valueOf((key + 1))).setValue(pushKey);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    break;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }


    public void writeToken() {
        DatabaseReference tokenReference = database.getReference(KeyLevel1.tokens.name());
//        final String pushKey = (tokenReference.push()).getKey();
        TokenModel tokenModel = new TokenModel();
        final String pushKey = (UUID.randomUUID().toString()).toUpperCase();
        tokenModel.setUniqueIdentifier(pushKey);
        tokenModel.setUser(PreferencesHelper.getInstance().getUniqueIdentifier());
        tokenReference.child(pushKey).setValue(tokenModel);

        DatabaseReference reference = database.getReference(KeyLevel1.gambles.name());
        // Write to first one only
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String uniqueIdentifier = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    uniqueIdentifier = (String) snapshot.child(KeyLevel3.pools.name()).child("0").getValue();
                    Log.e(TAG, "onDataChange: " + uniqueIdentifier);
                    // Create the pool
                    final DatabaseReference reference = database.getReference(KeyLevel1.pools.name()).child(uniqueIdentifier).child(KeyLevel3.tokens.name());
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int key = 0;
                            ArrayList<String> string = new ArrayList<String>();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                string.add(snapshot.getKey());
                            }
                            if (string.size() > 0) {
                                key = Integer.parseInt(string.get(string.size() - 1));
                                reference.child(String.valueOf((key + 1))).setValue(pushKey);
                            } else {
                                reference.child(String.valueOf((0))).setValue(pushKey);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    break;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public void setUserId() {
        DatabaseReference reference = database.getReference(KeyLevel1.users.name());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uniqueIdentifier = (String) snapshot.child(KeyLevel3.uniqueIdentifier.name()).getValue();
                    if (snapshot.child(KeyLevel3.firstName.name()).getValue().equals("Andy")) {
                        PreferencesHelper.getInstance().setUniqueIdentifier(uniqueIdentifier);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }


}
