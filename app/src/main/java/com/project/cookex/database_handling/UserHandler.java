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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserHandler {

    private static final String TAG = "UserHandler";

    private FirebaseAuth mAuth;
    private FirebaseUser fUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String KEY_FIRST_NAME = "First";
    private static final String KEY_MID_NAME = "Middle";
    private static final String KEY_LAST_NAME = "Last";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_BIRTHDAY = "Birthday";

    private String firstName, middleName, lastName;
    private String email, password, birthday;

    // Constructors - the proper one will be used depending on how much info users give during registry
    public UserHandler(String email) {
        this.email = email;
        this.mAuth = FirebaseAuth.getInstance();
        this.fUser = mAuth.getCurrentUser();
    }

    public UserHandler(String email, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mAuth = FirebaseAuth.getInstance();
        this.fUser = mAuth.getCurrentUser();
    }

    public UserHandler(String email, String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.mAuth = FirebaseAuth.getInstance();
        this.fUser = mAuth.getCurrentUser();
    }

    public UserHandler() {
        this.mAuth = FirebaseAuth.getInstance();
        this.fUser = mAuth.getCurrentUser();
        this.email = fUser.getEmail();
    }

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
    public void sendEmailVerification() {

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

    // Used this in P2 for something, it might become useful
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

    public void saveUser() {

        // Creates a reference to the Firebase Firestore collection named users
        CollectionReference users = db.collection("users");

        // Mapping the information given with the makeDocument method
        Map userInfo = makeDocument();

        // Add the mapped information to the users collection as a document with a generated ID
        users.add(userInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    // Depending on the information given during registry, the proper map will be created
    private Map<String, Object> makeDocument() {
        HashMap<String, Object> userInfo = new HashMap<>();

        userInfo.put(KEY_EMAIL, email);

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
