package com.project.cookex;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
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
import com.project.cookex.Model.RecipeModel;
import com.project.cookex.recipe_management.Recipe;

import java.util.ArrayList;

public class CreateRecipeActivity extends AppCompatActivity {

    private CreateRecipeAdapter createRecipeAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<RecipeModel> data;
    private Button buttonAddStep;
    private EditText name, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_recipe_activity);

        // Toolbar action
        initiateToolbar();
        //Creating steps with recycler view
        buildRecyclerView();

        buttonAddStep = (Button) findViewById(R.id.add_steps_button);
        data = new ArrayList<>();

        buttonAddStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateRecipeActivity.this);
                LayoutInflater dialogInflater = (LayoutInflater) CreateRecipeActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = dialogInflater.inflate(R.layout.list_create_steps, null);
                builder.setView(dialogView);

                name = (EditText) dialogView.findViewById(R.id.textInputLayout_step_name);
                description = (EditText) dialogView.findViewById(R.id.textInputLayoutDescription);

                builder.setTitle("Enter the step");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position;
                        if (data.size() == 0){
                            position = 0;
                        } else {
                            position = data.size();
                        }
                        String nameForStep = name.getText().toString().trim();
                        String descriptionForStep = description.getText().toString().trim();
                        RecipeModel currentItem = new RecipeModel(nameForStep, descriptionForStep);
                        //data = addItems(position, currentItem);
                        data.add(position, currentItem);
                        createRecipeAdapter = new CreateRecipeAdapter(data);
                        recyclerView.setAdapter(createRecipeAdapter);
                    }
                });
                builder.setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public ArrayList<RecipeModel> addItems(int position, RecipeModel recipeModel){
        ArrayList<RecipeModel> list = new ArrayList<>();
        list.add(position, recipeModel);
        return list;
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
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
