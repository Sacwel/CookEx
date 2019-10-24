package com.project.cookex.login_and_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.cookex.HomeActivity;
import com.project.cookex.R;

/**
 * THIS IS THE MAIN ACTIVITY
 * THE APP STARTS HERE WHEN LAUNCHED
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;

    private EditText mEmailField, mPasswordField;
    private TextView mStatusTextView;

    @Override
    protected void onStart() {

        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mEmailField = findViewById(R.id.loginEmailField);
        mPasswordField = findViewById(R.id.loginPasswordField);
        mStatusTextView = findViewById(R.id.statusTextView);

        Button mLoginButton = findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect inside app etc
                String email = mEmailField.getText().toString().trim();
                String password = mPasswordField.getText().toString().trim();
                signIn(email, password);
            }
        });

        TextView registerHyperlink = findViewById(R.id.registerHyperlink);
        registerHyperlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                goToRegister.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK + Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(goToRegister);
            }
        });
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Sign in Successful!", Toast.LENGTH_SHORT).show();

                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            updateUI(currentUser);

                        } else {

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Login Failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);

                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            mStatusTextView.setText(R.string.auth_failed);
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    // Form to validate inputs in the email/passwrd fields
    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {

            // Redirect the user inside the app after checking if he's already signed in
            System.out.println("User Signed In Successfully");
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            Intent loginSuccess = new Intent(LoginActivity.this, HomeActivity.class);
            loginSuccess.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK + Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginSuccess);

        } else {

            // Otherwise redirect to the sign in/sign up screen
            Toast.makeText(getApplicationContext(), "Login Failed. Try Again", Toast.LENGTH_SHORT).show();

        }
    }

}
