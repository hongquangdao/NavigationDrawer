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
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_HOME = 1;
    private static final int FRAGMENT_FAVORITE = 2;
    private static final int FRAGMENT_HISTORY = 3;
    private static final int FRAGMENT_PROFILE = 4;
    private static final int FRAGMENT_CHANGE_PASSWORD = 5;

    private int FRAGMENT_CURRENT = FRAGMENT_HOME;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new Home_fragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

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
}