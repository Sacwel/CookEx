package com.project.cookex.DatabaseHandling;

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

import static android.content.ContentValues.TAG;

public class UserHandler {

    public static String emailCredentials, passwordCredentials;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();

    // User type
    public static int USER_TYPE;
    public int getUserType() {
        return USER_TYPE;
    }
    public void setUserType(int i) { USER_TYPE = i; }

    // Collection reference
    private CollectionReference users = db.collection("users");

    private static final String KEY_TYPE = "Type";
    private static final String KEY_NAME = "Name";
    private static final String KEY_LAST_NAME = "Last Name";
    private static final String KEY_EMAIL = "Email";

    // Getters & Setters
    public static String getEmailCredentials() {
        return emailCredentials;
    }

    public static void setEmailCredentials(String emailCredentials) {
        UserHandler.emailCredentials = emailCredentials;
    }

    public static String getPasswordCredentials() {
        return passwordCredentials;
    }

    public static void setPasswordCredentials(String passwordCredentials) {
        UserHandler.passwordCredentials = passwordCredentials;
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
        user.put(KEY_TYPE, USER_TYPE);

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

    public void delete() {

        if (USER_TYPE == 1) {
            users.document(emailCredentials).delete();
        }

        if (USER_TYPE == 2){
            //If we want to create several types of users later on it should be specified here
        }
    }

}
