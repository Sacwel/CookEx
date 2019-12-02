package com.project.cookex;

import com.google.android.material.navigation.NavigationView;
import com.project.cookex.Bluetooth.DeviceControlActivity;
import com.project.cookex.Bluetooth.DeviceScanActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    public Toolbar mToolbar;
    private BluetoothAdapter bluetoothAdapter;
    private Button buttonForBluetooth;
    private Button buttonForScan;
    private int REQUEST_ENABLE_BT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerView.bringToFront();
                drawerView.requestLayout();
            }
        };

        buttonForScan = findViewById(R.id.Scan_button);
        buttonForScan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToScan = new Intent(HomeActivity.this, DeviceScanActivity.class);
                startActivity(goToScan);
            }
        } );
        buttonForBluetooth = findViewById(R.id.Bluetooth_button);
        buttonForBluetooth.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Initialize Bluetooth adapter
                final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                assert bluetoothManager != null;
                bluetoothAdapter = bluetoothManager.getAdapter();

                if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()){
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }
                Intent goToBluetooth = new Intent(HomeActivity.this, DeviceControlActivity.class);
                startActivity(goToBluetooth);
            }
        });

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextView viewProfile = header.findViewById(R.id.view_profile_button);
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFrag()).addToBackStack("TAG").commit();
                drawerLayout.closeDrawers();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.Create_recipe:
                Intent goToCreateRecipe = new Intent(HomeActivity.this, CreateRecipeActivity.class);
                startActivity(goToCreateRecipe);
                break;
            case R.id.scale_button:
                Intent goToScaleView = new Intent(HomeActivity.this, ScaleActivity.class);
                startActivity(goToScaleView);
                break;
            case R.id.Categories:
                Toast.makeText(this, "Categories", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Settings:
                Intent goToSettings = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(goToSettings);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
