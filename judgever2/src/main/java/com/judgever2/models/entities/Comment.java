package com.judgever2.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    private int score;
    private String textContent;
    private User author;
    private Homework homework;


    public Comment() {
    }

    @Column(name = "score", nullable = false)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Column(name = "text_content", nullable = false, columnDefinition = "text")
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }
}
