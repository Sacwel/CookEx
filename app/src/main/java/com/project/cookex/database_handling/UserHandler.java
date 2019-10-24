package com.project.cookex.database_handling;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserHandler {

    private String emailCredentials, passwordCredentials;

    private static final String TAG = "UserHandler";

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();

    // Collection reference
    private CollectionReference users = db.collection("users");

    private static final String KEY_NAME = "Name";
    private static final String KEY_LAST_NAME = "Last Name";
    private static final String KEY_EMAIL = "Email";

    public String getEmailCredentials() {
        return emailCredentials;
    }

    public void setEmailCredentials(String emailCredentials) {
        this.emailCredentials = emailCredentials;
    }

    public String getPasswordCredentials() {
        return passwordCredentials;
    }

    public void setPasswordCredentials(String passwordCredentials) {
        this.passwordCredentials = passwordCredentials;
    }

    public void emailVerification() {

        if (fUser != null) {
            fUser.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Email sent to: " + emailCredentials);
                            }
                        }
                    });
        }
    }

    public void saveUser(String firstNameVal, String lastNameVal, String emailVal) {
        Map<String, Object> user = new HashMap<>();
        user.put(KEY_NAME, firstNameVal);
        user.put(KEY_LAST_NAME, lastNameVal);
        user.put(KEY_EMAIL, emailVal);

        users.document(emailVal)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void reAuthUser() {
        // Get auth credentials from the user for re-authentication. The example below shows
        // email and password credentials but there are multiple possible providers,
        // such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider.getCredential(emailCredentials, passwordCredentials);

        // Prompt the user to re-provide their sign-in credentials
        fUser.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "User re-authenticated.");
                    }
                });
    }

    // Password reset method
    public void resetPassword() {
        mAuth.sendPasswordResetEmail(emailCredentials)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent to: " + emailCredentials);
                        }
                    }
                });
    }


}
