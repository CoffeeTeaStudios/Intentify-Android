package lawonga.intentify.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
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
import lawonga.intentify.view.fragment.ReloadFragment;

/**
 * Because this is a HACKATHON, that means I don't need to document code. HA HA!
 */

public class MainActivity extends BaseActivity implements
        PoolFragment.OnFragmentInteractionListener,
        BuyInFragment.OnFragmentInteractionListener,
        ReloadFragment.OnFragmentInteractionListener,
        CategoryFragment.OnFragmentInteractionListener {

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
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (tabId == R.id.tab_pot) {
                    PoolFragment poolFragment = PoolFragment.newInstance();
                    ft.replace(R.id.frameLayout, poolFragment);
                } else if (tabId == R.id.tab_discover) {
                    LocationHelper.getInstance().startListeningForUpdates();
                    DiscoverFragment discoverFragment = DiscoverFragment.getInstance();
                    ft.replace(R.id.frameLayout, discoverFragment);
                } else if (tabId == R.id.tab_my) {
                    MyFragment myFragment = MyFragment.getInstance();
                    ft.replace(R.id.frameLayout, myFragment);
                }
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        bottomBar.selectTabAtPosition(0);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onReloadPressed(Uri uri) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ReloadFragment reloadFragment = ReloadFragment.newInstance();
        ft.replace(R.id.frameLayout, reloadFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
