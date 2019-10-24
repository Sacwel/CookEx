package com.project.cookex;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar toolbar;
    private TextView mViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {super.onDrawerClosed(drawerView);}

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerView.bringToFront();
                drawerView.requestLayout();
            }
        };

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        final NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        mViewProfile = header.findViewById(R.id.view_profile_button);
        mViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileActivity()).commit();
                drawerLayout.closeDrawers();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.Create_recipe:
                Toast.makeText(this, "Create a recipe", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Categories:
                Toast.makeText(this, "Categories", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsAcrivity()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
