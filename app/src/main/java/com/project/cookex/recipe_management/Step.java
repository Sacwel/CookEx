package com.project.cookex.recipe_management;

// TODO: 2019-12-15
// The idea is to split recipe creation into 2 parts where the first is the stepName and stepDescription
// Then when starting to add steps (instances of this class), each of these should be added to an ArrayList
// After adding all steps and pressing save, this List should then be merged into the document created in part 1

public class Step {

    private String stepName;
    private String stepDescription;

    public Step(String stepName, String stepDescription) {
        this.stepName = stepName;
        this.stepDescription = stepDescription;
    }

    public String getName() {
        return stepName;
    }
    public void setName(String stepName) {
        this.stepName = stepName;
    }

    public String getDescription() {
        return stepDescription;
    }
    public void setDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }
}
