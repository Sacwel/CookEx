package com.project.cookex.login_and_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.project.cookex.database_handling.UserHandler;
import com.project.cookex.HomeActivity;
import com.project.cookex.R;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "EmailPasswordSignup";

    private FirebaseAuth mAuth;
    private UserHandler uDBHandler;

    private EditText mEmailField, mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        uDBHandler = new UserHandler();

        mEmailField = findViewById(R.id.signupEmailField);
        mPasswordField = findViewById(R.id.signupPassField);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email = mEmailField.getText().toString().trim();
               String password = mPasswordField.getText().toString().trim();

               createAccount(email, password);
            }
        });
    }

    // Firebase's own createAccount method with some personalization
    public void createAccount(final String email, final String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Account creation success, update UI with the users saved information
                            saveUser(email, password);
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                        } else {

                            // If sign up fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Registration Failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        // [END create_user_with_email]
    }

    // Creates an instance of the handler class and calls it's saveUser method
    private void saveUser(String email, String password) {
        uDBHandler = new UserHandler(email, password);
        uDBHandler.saveUser();
        uDBHandler.sendEmailVerification();

        // Start the home activity and flag this one for destruction
        Intent registrationSuccess = new Intent(RegisterActivity.this, HomeActivity.class);
        registrationSuccess.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK + Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(registrationSuccess);
    }

    // Method to check if all fields on the registry page is filled in properly
    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Email Required.");
            mEmailField.requestFocus();
            valid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailField.setError("Valid Email Required.");
            mEmailField.requestFocus();
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Password Required.");
            mPasswordField.requestFocus();
            valid = false;
        } else if (password.length() < 6) {
            mPasswordField.setError("Password must be at least 6 characters");
            mPasswordField.requestFocus();

        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }
}
