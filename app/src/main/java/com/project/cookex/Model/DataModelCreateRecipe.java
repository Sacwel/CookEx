package com.project.cookex.Model;

public class DataModelCreateRecipe {

    public String editName;
    public String editDescription;
    public String takeCategory;

    public void DataModelCreateRecipe (String name, String description, String category){
        this.editName = name;
        this.editDescription = description;
        this.takeCategory = category;
    }

    public String getEditName() {
        return editName;
    }

    public void setEditName(String editName) {
        this.editName = editName;
    }

    public String getEditDescription() {
        return editDescription;
    }

    public void setEditDescription(String editDescription) {
        this.editDescription = editDescription;
    }

    public String getTakeCategory() {
        return takeCategory;
    }

    public void setTakeCategory(String takeCategory) {
        this.takeCategory = takeCategory;
    }
}
