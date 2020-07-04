package com.judgever2.models.serviceModels;

import java.time.LocalDateTime;

public class ExerciseServiceModel extends BaseServiceModel{
    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;

    public ExerciseServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
