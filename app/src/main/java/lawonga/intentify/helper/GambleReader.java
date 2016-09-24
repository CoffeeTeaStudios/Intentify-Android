package lawonga.intentify.helper;

import java.util.ArrayList;

/**
 * Created by Andy on 9/24/2016.
 * Reader for gamble information
 */
public class GambleReader {
    private static GambleReader ourInstance = new GambleReader();

    public static GambleReader getInstance() {
        return ourInstance;
    }

    private GambleReader() {
    }

    /**
     * Called from discover list. Read from it in an array
     */
    public void readDiscoverList() {

    }

    /**
     * Called from my pool. Read from pools.
     */
    public void readMyPools() {

    }

    /**
     * Read my profile. Called from a profile screen.
     */
    public void readMyProfile() {

    }

    /**
     * I guess we create a bunch of category and read from it
     * Called upon application startup
     */
    ArrayList<String> categories = new ArrayList<>();

    public void initCategories() {
        categories.add("SportsCheck");
        categories.add("BCLC");
        categories.add("Telus");
        categories.add("Bob's Gamble");
        categories.add("Only Cool People Allowed");
        categories.add("Accountant's Pool");
        categories.add("Programmer's Pool");
    }

    /**
     * Read from categories
     *
     * @return categories
     */
    public ArrayList<String> getCategories() {
        return categories;
    }
}
