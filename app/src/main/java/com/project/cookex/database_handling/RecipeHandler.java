package com.project.cookex.database_handling;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.project.cookex.recipe_management.Recipe;
import com.project.cookex.recipe_management.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// TODO: 2019-12-15
// This class should be solely responsible for all database interactions

public class RecipeHandler {

    private static final String TAG = "RecipeHandler";
    private static final String KEY_PICTURE_URI = "Picture";
    private static final String KEY_TITLE = "Title";
    private static final String KEY_CATEGORY = "Category";
    private static final String KEY_DESCRIPTION = "Description";
    private static final String KEY_STEPS = "Steps";

    private FirebaseAuth mAuth;
    private FirebaseUser fUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String recipeName, description, category;
    private ArrayList<Step> steps;

    public RecipeHandler(Recipe recipe) {
        this.recipeName = recipe.getName();
        this.description = recipe.getDescription();
        this.steps = recipe.getSteps();
        this.mAuth = FirebaseAuth.getInstance();
        this.fUser = mAuth.getCurrentUser();
    }

    // Storing recipes in Firebase
    public void addRecipe() {

        // Creates a reference to the Firebase Firestore collection named recipes
        CollectionReference recipes = db.collection("Recipes");

        // Mapping the information given with the makeRecipeDoc method
        Map recipeInfo = makeRecipeDoc();

        // Add the mapped information to the recipes collection as a document with the recipe name as ID
        recipes.document(recipeName)
                .set(recipeInfo, SetOptions.merge())
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

    // Method for creating the recipe documents
    private Map<String, Object> makeRecipeDoc() {
        HashMap<String, Object> recipeInfo = new HashMap<>();

        recipeInfo.put(KEY_TITLE, recipeName);
        recipeInfo.put(KEY_DESCRIPTION, description);
        recipeInfo.put(KEY_STEPS, steps);

        return recipeInfo;
    }

}
