package com.project.cookex.recipe_management;

public abstract class Recipe {

    private String name, category;
    private String[] ingredients, tags;
    private int rating, priceLevel, servings;
    private double prepTime, cookTime;

    public Recipe(String name, String[] ingredients, String category, String[] tags, int rating, int priceLevel, int servings, double prepTime, double cookTime) {
        this.name = name;
        this.ingredients = ingredients;
        this.category = category;
        this.tags = tags;
        this.rating = rating;
        this.priceLevel = priceLevel;
        this.servings = servings;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String[] getIngredients() {
        return ingredients;
    }
    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getTags() {
        return tags;
    }
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPriceLevel() {
        return priceLevel;
    }
    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    public int getServings() {
        return servings;
    }
    public void setServings(int servings) {
        this.servings = servings;
    }

    public double getPrepTime() {
        return prepTime;
    }
    public void setPrepTime(double prepTime) {
        this.prepTime = prepTime;
    }

    public double getCookTime() {
        return cookTime;
    }
    public void setCookTime(double cookTime) {
        this.cookTime = cookTime;
    }
}
