package lawonga.intentify.model;

import java.util.ArrayList;

/**
 * Created by Andy on 9/24/2016.
 */

public class PoolModel {
    private ArrayList<String> userIds; // Can have duplicates; that's for different entries
    private double maxPool;
    private double currentPool;

    public ArrayList<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(ArrayList<String> userIds) {
        this.userIds = userIds;
    }

    public double getMaxPool() {
        return maxPool;
    }

    public void setMaxPool(double maxPool) {
        this.maxPool = maxPool;
    }

    public double getCurrentPool() {
        return currentPool;
    }

    public void setCurrentPool(double currentPool) {
        this.currentPool = currentPool;
    }
}
