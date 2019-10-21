package com.project.cookex;

public class Recipes {

    private int cookingTime;
    private String rName, rIngredients, rMeasures;
    private String rDescription, rGuide, rTags;

    public Recipes() {}

    public Recipes(String rName, String rTags) {
        this.rName = rName;
        this.rTags = rTags;
    }

    public Recipes(String rName) {
        this.rName = rName;
    }

    public Recipes(String rName, String rDescription, String rTags) {
        this.rName = rName;
        this.rDescription = rDescription;
        this.rTags = rTags;
    }

    public int getCookingTimes() {
        return cookingTime;
    }

    public void setCookingTimes(int cookingTimes) {
        this.cookingTime = cookingTimes;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrIngredients() {
        return rIngredients;
    }

    public void setrIngredients(String rIngredients) {
        this.rIngredients = rIngredients;
    }

    public String getrMeasures() {
        return rMeasures;
    }

    public void setrMeasures(String rMeasures) {
        this.rMeasures = rMeasures;
    }

    public String getrDescription() {
        return rDescription;
    }

    public void setrDescription(String rDescription) {
        this.rDescription = rDescription;
    }

    public String getrGuide() {
        return rGuide;
    }

    public void setrGuide(String rGuide) {
        this.rGuide = rGuide;
    }

    public String getrTags() {
        return rTags;
    }

    public void setrTags(String rTags) {
        this.rTags = rTags;
    }
}
