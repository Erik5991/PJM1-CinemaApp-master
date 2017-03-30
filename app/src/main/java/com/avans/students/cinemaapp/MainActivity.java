package com.avans.students.cinemaapp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.avans.students.cinemaapp.utils.AppFragmentController;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static ActionBar sActionBar;
    private DrawerLayout drawer;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeToolbar();
        setStatusBarColor();
        initializeNavigationView();

        startHomeFragment();


    }

    /**
     * Set AppFragmentController with the FragmentManager AND Load Home Fragment and set NavigationView checked with Home
     */
    private void startHomeFragment() {
        AppFragmentController.sFragmentManager = getSupportFragmentManager();
        AppFragmentController.getHomeFragment();
        navigationView.setCheckedItem(R.id.nav_home);
    }

    /**
     * Define the navigationView, sets the NavigationItemSelectedListener and sets the Home menu button as Selected
     */
    private void initializeNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Define the view of the custom toolbar, set custom toolbar as SupportActionBar.
     * Runs the initializeDrawer() method with the defined Toolbar.
     */
    private void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sActionBar = getSupportActionBar();

        initializeDrawer(toolbar);
    }

    /**
     * Initialise the drawer, creates the ActionBarDrawerToggle and set de toggle to the DrawerLayout
     * @param toolbar The toolbar to use for Toggle
     */
    private void initializeDrawer(Toolbar toolbar) {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     *  Sets the StatusBarColor to colorPrimaryDark on Devices where SDK >= LOLLIPOP (SDK 21)
     */
    private void setStatusBarColor() {
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
    }

    /**
     * onNavigationItemSelected Listener for Drawer, sets the chosen Fragment and close drawer
     * @param item MenuItem is The selected item
     * @return Boolean true
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                AppFragmentController.getHomeFragment();
                break;
            case R.id.nav_now_in_cinema:
                AppFragmentController.getNowInCinemaFragment();
                break;
            case R.id.nav_to_be_expected:
                AppFragmentController.getToBeExpectedFragment();
                break;
            case R.id.nav_deals_events:
                AppFragmentController.getDealsEventsFragment();
                break;
            case R.id.nav_benefit_card:
                AppFragmentController.getBenefitCardFragment();
                break;
            case R.id.nav_contact:
                AppFragmentController.getContactFragment();
                break;
        }
        drawer.closeDrawers();
        return true;
    }
}
