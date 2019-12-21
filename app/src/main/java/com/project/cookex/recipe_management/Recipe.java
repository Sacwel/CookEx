package com.project.cookex.recipe_management;

import java.util.ArrayList;

// TODO: 2019-12-15
// Changed the name from 'RecipeModel' and merged with my already existing Recipe class

public class Recipe {

    private String name, description;
    private String stepDescription;
    private ArrayList<Step> steps;

    public Recipe(String name, String description, ArrayList<Step> steps) {
        this.name = name;
        this.description = description;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStepDescription() {
        return stepDescription;
    }
    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }
    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
}
