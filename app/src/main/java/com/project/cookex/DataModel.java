package com.project.cookex;

import android.widget.Spinner;

public class DataModel {

    String name;
    String description;
    int imageResource;
    String rating;

    public DataModel(String name, String description, int imageResource, String rating){
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.imageResource = imageResource;
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

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
