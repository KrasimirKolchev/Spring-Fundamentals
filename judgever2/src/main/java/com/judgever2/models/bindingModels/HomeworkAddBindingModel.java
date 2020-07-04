package com.judgever2.models.bindingModels;

import javax.validation.constraints.Pattern;

public class HomeworkAddBindingModel {
    private String gitAddress;
    private String exercise; //get exercise id

    public HomeworkAddBindingModel() {
    }

    @Pattern(regexp = "https:\\/github\\.com\\/.+\\/.+\\/",
            message = "Enter git address following this pattern: https:/github.com/{username}/{homeworkExample}/")
    public String getGitAddress() {
        return gitAddress;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
}
