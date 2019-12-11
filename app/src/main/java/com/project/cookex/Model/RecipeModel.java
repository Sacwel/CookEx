package com.project.cookex.Model;

public class RecipeModel {

    private String name;
    private String description;
    private String takeCategory;

    public RecipeModel(String name, String description){
        this.name = name;
        this.description = description;
        //this.takeCategory = category;
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

    public String getTakeCategory() {
        return takeCategory;
    }

    public void setTakeCategory(String takeCategory) {
        this.takeCategory = takeCategory;
    }
}
