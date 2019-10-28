package com.project.cookex;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.project.cookex.login_and_register.LoginActivity;
import com.project.cookex.login_and_register.RegisterActivity;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "EmailPasswordSignup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Button signOutButton = findViewById(R.id.SignOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent signOutSuccess = new Intent(SettingsActivity.this, LoginActivity.class);
                //signOutSuccess.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK + Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(signOutSuccess);
            }
        });

    }
}
