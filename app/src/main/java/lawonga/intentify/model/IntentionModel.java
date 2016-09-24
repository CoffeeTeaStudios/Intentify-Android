package lawonga.intentify.model;

/**
 * Created by Andy on 9/23/2016.
 */
public class IntentionModel {
    private String itemName, description;
    private long price, time;
    private boolean isBuying;

    public IntentionModel(String itemName, String description, long price, long time, boolean isBuying) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.time = time;
        this.isBuying = isBuying;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isBuying() {
        return isBuying;
    }

    public void setBuying(boolean buying) {
        isBuying = buying;
    }
}
