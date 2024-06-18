package io.codechallenge.survey.service.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Answer> answers;

    public List<Answer> answers() {
        return answers;
    }

    public Question setAnswers(List<Answer> answers) {
        this.answers = answers;
        return this;
    }

    public Long id() {
        return id;
    }

    public Question setId(Long id) {
        this.id = id;
        return this;
    }

    public String text() {
        return text;
    }

    public Question setText(String text) {
        this.text = text;
        return this;
    }
}
