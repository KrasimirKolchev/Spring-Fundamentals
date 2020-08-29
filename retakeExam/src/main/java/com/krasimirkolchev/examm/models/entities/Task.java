package com.krasimirkolchev.examm.models.entities;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    private String name;
    private String description;
    private String progress;
    private Date dueDate;
    private Classification classification;
    private String user;

    public Task() {
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "progress")
    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Column(name = "due_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @ManyToOne
    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    @Column(name = "userId", nullable = false)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
