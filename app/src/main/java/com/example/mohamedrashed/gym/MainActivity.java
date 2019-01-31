package com.example.mohamedrashed.gym;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.mohamedrashed.gym.BottomNavigation.AccountFragment;
import com.example.mohamedrashed.gym.BottomNavigation.FavoriteFragment;
import com.example.mohamedrashed.gym.BottomNavigation.SearchFragment;
import com.example.mohamedrashed.gym.BottomNavigation.NewsFragment;
import com.example.mohamedrashed.gym.BottomNavigation.StoreFragment;
import com.example.mohamedrashed.gym.Login.Login;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.getMenu().findItem(R.id.nav_search).setChecked(true);

        bottomNav.setOnNavigationItemSelectedListener(navListener);


        // Test Commit By

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SearchFragment()).commit();


            /*viewPager = (ViewPager) findViewById(R.id.viewPager);
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

            viewPager.setAdapter(viewPagerAdapter);*/

        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.nav_favorite:
                            selectedFragment = new FavoriteFragment();
                            break;
                        case R.id.nav_news:
                            selectedFragment = new NewsFragment();
                            break;
                        case R.id.nav_store:
                            selectedFragment = new StoreFragment();
                            break;
                        case R.id.nav_account:
                            selectedFragment = new AccountFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

}
