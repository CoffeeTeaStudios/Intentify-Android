package lawonga.intentify.helper;

import lawonga.intentify.keys.KeyLevel1;
import lawonga.intentify.keys.KeyLevel2;
import lawonga.intentify.model.IntentionModel;

/**
 * Created by Andy on 9/23/2016.
 */
public class IntentionHelper {
    private static IntentionHelper ourInstance = new IntentionHelper();

    public static IntentionHelper getInstance() {
        return ourInstance;
    }

    private IntentionHelper() {
    }

    /**
     * Intention to buy
     */
    public void setIntention(IntentionModel intentionModel) {
        FirebaseHelper firebaseHelper = FirebaseHelper.getInstance();
        firebaseHelper.setKeyLevel1(KeyLevel1.INTENTIONS);
        firebaseHelper.write(KeyLevel2.ITEM_NAME, intentionModel.getItemName());
        firebaseHelper.write(KeyLevel2.DESCRIPTION, intentionModel.getDescription());
        firebaseHelper.write(KeyLevel2.IS_BUYING_BOOLEAN, intentionModel.isBuying());
        firebaseHelper.write(KeyLevel2.PRICE, intentionModel.getPrice());
    }
}
