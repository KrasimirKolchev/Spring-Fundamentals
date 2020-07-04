package com.judgever2.models.serviceModels;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class HomeworkServiceModel extends BaseServiceModel {
    private LocalDateTime addedOn;
    private String gitAddress;
    private String author;
    private ExerciseServiceModel exercise;

    public HomeworkServiceModel() {
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public String getGitAddress() {
        return gitAddress;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ExerciseServiceModel getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseServiceModel exercise) {
        this.exercise = exercise;
    }
}
