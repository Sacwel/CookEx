package com.project.cookex;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.cookex.Adapters.CreateRecipeAdapter;
import com.project.cookex.Model.DataModelCreateRecipe;

import java.util.ArrayList;

public class CreateRecipeActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModelCreateRecipe> data;
    CreateRecipeAdapter createRecipeAdapter;
    private Button buttonAddStep;
    private ImageView buttonRemoveStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_recipe_activity);

        // Toolbar action
        initiateToolbar();
        //Creating steps with recycler view
        buildRecyclerView();

        buttonAddStep = (Button) findViewById(R.id.add_steps_button);
        data = new ArrayList<DataModelCreateRecipe>();
        buttonAddStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = data.size();
                data = insertItem(position);
            }
        });
    }

    public ArrayList<DataModelCreateRecipe> insertItem(int position){
        ArrayList<DataModelCreateRecipe> list = new ArrayList<>();
        DataModelCreateRecipe dataModelCreateRecipe = new DataModelCreateRecipe();
        dataModelCreateRecipe.setEditName(String.valueOf(position));
        dataModelCreateRecipe.setEditDescription(String.valueOf(position));
        dataModelCreateRecipe.setTakeCategory(String.valueOf(position));
        list.add(dataModelCreateRecipe);
        adapter.notifyDataSetChanged();
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
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_create_recipe);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        createRecipeAdapter = new CreateRecipeAdapter(this, data);
        recyclerView.setAdapter(adapter);
    }
}
