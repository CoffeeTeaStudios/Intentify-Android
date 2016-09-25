package lawonga.intentify.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import lawonga.intentify.R;
import lawonga.intentify.base.BaseActivity;
import lawonga.intentify.helper.LocationHelper;
import lawonga.intentify.view.fragment.BuyInFragment;
import lawonga.intentify.view.fragment.CategoryFragment;
import lawonga.intentify.view.fragment.DiscoverFragment;
import lawonga.intentify.view.fragment.MyFragment;
import lawonga.intentify.view.fragment.PoolFragment;
import lawonga.intentify.view.fragment.PresenterFragment;
import lawonga.intentify.view.fragment.ReloadFragment;

/**
 * Because this is a HACKATHON, that means I don't need to document code. HA HA!
 */

public class MainActivity extends BaseActivity implements
        PoolFragment.OnFragmentInteractionListener,
        BuyInFragment.OnFragmentInteractionListener,
        ReloadFragment.OnFragmentInteractionListener,
        CategoryFragment.OnFragmentInteractionListener {

    private static String TAG = "MainActivity";

    @BindView(R.id.frameLayout) FrameLayout frameLayout;
    @BindView(R.id.bottomBar) BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                if (tabId == R.id.tab_discover) {
                    CategoryFragment categoryFragment = CategoryFragment.newInstance();
                    fragmentTransaction.replace(R.id.frameLayout, categoryFragment);
                } else if (tabId == R.id.tab_my) {
                    MyFragment myFragment = MyFragment.getInstance();
                    fragmentTransaction.replace(R.id.frameLayout, myFragment);
                }
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

//        bottomBar.selectTabAtPosition(0);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PresenterFragment presenterFragment = PresenterFragment.getInstance();
        fragmentTransaction.replace(R.id.frameLayout, presenterFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            Toast.makeText(MainActivity.this, "Filter Clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_buy_in) {
            // Buy in
            onBuyInPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onReloadPressed(Uri uri) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right,
                R.anim.enter_from_right, R.anim.exit_from_right);
        ReloadFragment reloadFragment = ReloadFragment.newInstance();
        fragmentTransaction.replace(R.id.frameLayout, reloadFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void onBuyInPressed() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right,
                R.anim.enter_from_right, R.anim.exit_from_right);
        BuyInFragment buyInFragment = BuyInFragment.getInstance();
        fragmentTransaction.replace(R.id.frameLayout, buyInFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void onCategoryClicked() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        DiscoverFragment discoverFragment = DiscoverFragment.getInstance();
        fragmentTransaction.replace(R.id.frameLayout, discoverFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Log.e(TAG, "onCategoryClicked: alkdsfjkljasdflkj");
    }
}
