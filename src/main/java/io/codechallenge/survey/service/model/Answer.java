package io.codechallenge.survey.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    public String text() {
        return text;
    }

    public Answer setText(String text) {
        this.text = text;
        return this;
    }

    public Long id() {
        return id;
    }

    public Answer setId(Long id) {
        this.id = id;
        return this;
    }
}
