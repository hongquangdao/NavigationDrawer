package com.example.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.navigationdrawer.Fragment.ChangePassword_fragment;
import com.example.navigationdrawer.Fragment.Favorite_fragment;
import com.example.navigationdrawer.Fragment.History_fragment;
import com.example.navigationdrawer.Fragment.Home_fragment;
import com.example.navigationdrawer.Fragment.Profile_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_HOME = 1;
    private static final int FRAGMENT_FAVORITE = 2;
    private static final int FRAGMENT_HISTORY = 3;
    private static final int FRAGMENT_PROFILE = 4;
    private static final int FRAGMENT_CHANGE_PASSWORD = 5;

    private int FRAGMENT_CURRENT = FRAGMENT_HOME;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitleBar();

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.Nav_open,
                R.string.Nav_close
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new Home_fragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        mBottomNavigationView = findViewById(R.id.bottom_nav);
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        replaceFragment(new Home_fragment());
                        break;

                    case R.id.action_settings:
                        replaceFragment(new Favorite_fragment());
                        break;

                    case R.id.action_navigation:
                        replaceFragment(new History_fragment());
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_home) {
            if (FRAGMENT_CURRENT != FRAGMENT_HOME) {
                replaceFragment(new Home_fragment());
                FRAGMENT_CURRENT = FRAGMENT_HOME;
            }
        } else if (id == R.id.nav_favorite) {
            if (FRAGMENT_CURRENT != FRAGMENT_FAVORITE) {
                replaceFragment(new Favorite_fragment());
                FRAGMENT_CURRENT = FRAGMENT_FAVORITE;
            }
        } else if (id == R.id.nav_my_profile) {
            if (FRAGMENT_CURRENT != FRAGMENT_PROFILE) {
                replaceFragment(new Profile_fragment());
                FRAGMENT_CURRENT = FRAGMENT_PROFILE;
            }
        } else if (id == R.id.nav_change_password) {
            if (FRAGMENT_CURRENT != FRAGMENT_CHANGE_PASSWORD) {
                replaceFragment(new ChangePassword_fragment());
                FRAGMENT_CURRENT = FRAGMENT_CHANGE_PASSWORD;
            }
        } else if (id == R.id.nav_history) {
            if (FRAGMENT_CURRENT != FRAGMENT_HISTORY) {
                replaceFragment(new History_fragment());
                FRAGMENT_CURRENT = FRAGMENT_HISTORY;
            }
        }

        setTitleBar();
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    private void setTitleBar() {
        String title = "";
        switch (FRAGMENT_CURRENT) {
            case FRAGMENT_HOME:
                title = getString(R.string.Nav_home);
                break;
            case FRAGMENT_FAVORITE:
                title = getString(R.string.Nav_favorite);
                break;
            case FRAGMENT_HISTORY:
                title = getString(R.string.Nav_history);
                break;

            case FRAGMENT_PROFILE:
                title = getString(R.string.Nav_profile);
                break;

            case FRAGMENT_CHANGE_PASSWORD:
                title = getString(R.string.Nav_change_Password);
                break;
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}