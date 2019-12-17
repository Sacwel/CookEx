package com.project.cookex;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.cookex.Adapters.CreateRecipeAdapter;
import com.project.cookex.database_handling.RecipeHandler;
import com.project.cookex.recipe_management.Recipe;
import com.project.cookex.recipe_management.Step;

import java.util.ArrayList;

// TODO: 2019-12-15
// Should be structured for better overview and properly use both Recipe and Handler classes
// Works as supposed right now but help is needed for the database implementation

public class CreateRecipeActivity extends AppCompatActivity {

    private CreateRecipeAdapter createRecipeAdapter;
    private static RecyclerView recyclerView;
    private static ArrayList<Step> steps;
    private EditText mStepNameField, mStepDescriptionField;
    private EditText mRecipeNameField, mRecipeDescriptionField;
    private RecipeHandler mRecipeHandler;
    private String nameOfRecipe, descriptionOfRecipe;
    private String nameOfStep, descriptionOfStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_recipe_activity);

        // Toolbar action
        initiateToolbar();
        //Creating steps with recycler view
        buildRecyclerView();

        mRecipeNameField = findViewById(R.id.nameOfRecipe);
        mRecipeDescriptionField = findViewById(R.id.recipeDescription);

        Button buttonAddStep = (Button) findViewById(R.id.add_steps_button);
        steps = new ArrayList<>();

        buttonAddStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CreateRecipeActivity.this);
                LayoutInflater dialogInflater = (LayoutInflater) CreateRecipeActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = dialogInflater.inflate(R.layout.list_create_steps, null);
                builder.setView(dialogView);

                mStepNameField = (EditText) dialogView.findViewById(R.id.nameOfStep);
                mStepDescriptionField = (EditText) dialogView.findViewById(R.id.descriptionOfStep);

                builder.setTitle("Enter the step");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int index;
                        if (steps.size() == 0){
                            index = 0;
                        } else {
                            index = steps.size();
                        }
                        nameOfStep = mStepNameField.getText().toString().trim();
                        descriptionOfStep = mStepDescriptionField.getText().toString().trim();
                        Step currentItem = new Step(nameOfStep, descriptionOfStep);
                        steps.add(index, currentItem);

                        createRecipeAdapter = new CreateRecipeAdapter(steps);
                        recyclerView.setAdapter(createRecipeAdapter);
                    }
                });
                builder.setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button saveStepsButton = findViewById(R.id.saveRecipeStepsButton);
        steps = new ArrayList<>();

        saveStepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mRecipeNameField.getText().toString().trim();
                String description = mRecipeDescriptionField.getText().toString().trim();

                Recipe currentRecipe = conjureRecipe(name, description, steps);
                mRecipeHandler = new RecipeHandler(currentRecipe);
                mRecipeHandler.addRecipe();
            }
        });

    }

    private Recipe conjureRecipe(String name, String desc, ArrayList<Step> steps) {
        return new Recipe(name, desc, steps);
    }

    public void initiateToolbar(){
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public void buildRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view_create_recipe);
        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
