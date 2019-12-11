package com.project.cookex.database_handling;

import com.project.cookex.Model.RecipeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeHandler {

    private static final String TAG = "RecipeHandler";
    private static final String KEY_PICTURE_URI = "Picture";
    private static final String KEY_TITLE = "Title";
    private static final String KEY_CATEGORY = "Category";
    private static final String KEY_DESCRIPTION = "Description";

    private String title, description;
    private String category;
    private String step;

    private Map<String, RecipeModel> stepMap = new HashMap<>();
    private List<RecipeModel> stepsArr = new ArrayList<>();

    private void addSteps(int steps) {

        for (RecipeModel r : stepsArr) {
            int index = 1;
            stepMap.put(step+" "+index+": ", r);
        }

    }

    public RecipeHandler(String title, String description) {
        this.title = title;
        this.description = description;
        step = "Step";
    }

    private Map<String, Object> makeRecipeDoc() {
        HashMap<String, Object> recipeInfo = new HashMap<>();

        recipeInfo.put(KEY_TITLE, title);
        recipeInfo.put(KEY_DESCRIPTION, description);

        return recipeInfo;
    }

}
