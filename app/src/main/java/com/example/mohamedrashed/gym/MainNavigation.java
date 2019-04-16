package com.example.mohamedrashed.gym;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mohamedrashed.gym.Admin.AddGym;
import com.example.mohamedrashed.gym.BottomNavigation.AccountFragment;
import com.example.mohamedrashed.gym.BottomNavigation.FavoriteFragment;
import com.example.mohamedrashed.gym.BottomNavigation.NewsFragment;
import com.example.mohamedrashed.gym.BottomNavigation.SearchFragment;
import com.example.mohamedrashed.gym.BottomNavigation.StoreFragment;

public class MainNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Nav Position
    NavigationView drawerNavView;
    BottomNavigationView bottomNavView;

    // ADMIN CONTROL
    public static short ADMIN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Get Drawer Item
        drawerNavView = (NavigationView) findViewById(R.id.nav_view);
        drawerNavView.getMenu().findItem(R.id.nav_search).setChecked(true);
        drawerNavView.setNavigationItemSelectedListener(this);

        // Get Bottom Item
        bottomNavView = findViewById(R.id.navigation_bottom);
        bottomNavView.getMenu().findItem(R.id.nav_search).setChecked(true);
        bottomNavView.setOnNavigationItemSelectedListener(navListener);

        // Fragments Code

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SearchFragment()).commit(); // this new for the select main fragment view

        }

        // ADMIN CONTROL
        if (ADMIN == 1) {
            drawerNavView.getMenu().findItem(R.id.nav_admin).setVisible(true);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Navigation
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.nav_search:
                selectedFragment = new SearchFragment();
                bottomNavView.getMenu().findItem(R.id.nav_search).setChecked(true);
                break;
            case R.id.nav_favorite:
                selectedFragment = new FavoriteFragment();
                bottomNavView.getMenu().findItem(R.id.nav_favorite).setChecked(true);
                break;
            case R.id.nav_news:
                selectedFragment = new NewsFragment();
                bottomNavView.getMenu().findItem(R.id.nav_news).setChecked(true);
                break;
            case R.id.nav_store:
                selectedFragment = new StoreFragment();
                bottomNavView.getMenu().findItem(R.id.nav_store).setChecked(true);
                break;
            case R.id.nav_account:
                selectedFragment = new AccountFragment();
                bottomNavView.getMenu().findItem(R.id.nav_account).setChecked(true);
                break;

            // ADMIN CONTROL
            case R.id.nav_admin:
                selectedFragment = new SearchFragment();
                startActivity(new Intent(getApplicationContext(), AddGym.class));
                Toast.makeText(this, "Welcome ADMIN", Toast.LENGTH_SHORT).show();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            drawerNavView.getMenu().findItem(R.id.nav_search).setChecked(true);
                            break;
                        case R.id.nav_favorite:
                            selectedFragment = new FavoriteFragment();
                            drawerNavView.getMenu().findItem(R.id.nav_favorite).setChecked(true);
                            break;
                        case R.id.nav_news:
                            selectedFragment = new NewsFragment();
                            drawerNavView.getMenu().findItem(R.id.nav_news).setChecked(true);
                            break;
                        case R.id.nav_store:
                            selectedFragment = new StoreFragment();
                            drawerNavView.getMenu().findItem(R.id.nav_store).setChecked(true);
                            break;
                        case R.id.nav_account:
                            selectedFragment = new AccountFragment();
                            drawerNavView.getMenu().findItem(R.id.nav_account).setChecked(true);
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}
