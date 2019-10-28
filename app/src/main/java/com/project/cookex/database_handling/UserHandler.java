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

    private static final String TAG = "UserHandler";

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String KEY_FIRST_NAME = "First";
    private static final String KEY_MID_NAME = "Middle";
    private static final String KEY_LAST_NAME = "Last";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD = "Password";
    private static final String KEY_BIRTHDAY = "Birthday";

    private String firstName, middleName, lastName;
    private String email, password, birthday;

    // Constructors
    public UserHandler(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserHandler(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserHandler(String firstName, String middleName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserHandler() {}

    // Getters & Setters
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    // Firebase stuff
    public void emailVerification() {

        if (fUser != null) {
            fUser.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Email sent to: " + email);
                            }
                        }
                    });
        }
    }

    public void reAuthUser() {
        // Get auth credentials from the user for re-authentication. The example below shows
        // email and password credentials but there are multiple possible providers,
        // such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider.getCredential(email, password);

        // Prompt the user to re-provide their sign-in credentials
        fUser.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "User re-authenticated.");
                    }
                });
    }

    public void resetPassword() {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent to: " + email);
                        }
                    }
                });
    }

    public void saveUser(final String email) {
        CollectionReference users = db.collection("users");

        Map userInfo = makeDocument();
        users.document(email)
                .set(userInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written with ID: " + email);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    private Map<String, Object> makeDocument() {
        HashMap<String, Object> userInfo = new HashMap<>();

        userInfo.put(KEY_EMAIL, email);
        userInfo.put(KEY_PASSWORD, password);

        if (firstName != null) {
            userInfo.put(KEY_FIRST_NAME, firstName);
        }

        if (middleName != null) {
            userInfo.put(KEY_MID_NAME, middleName);
        }

        if (lastName != null) {
            userInfo.put(KEY_LAST_NAME, lastName);
        }

        if (birthday != null) {
            userInfo.put(KEY_BIRTHDAY, birthday);
        }

        return userInfo;
    }

}
