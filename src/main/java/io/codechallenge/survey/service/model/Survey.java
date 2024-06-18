package io.codechallenge.survey.service.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Question> questions = new ArrayList<>();

    public List<Question> questions() {
        return questions;
    }

    public Survey setQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Long id() {
        return id;
    }

    public Survey setId(Long id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public Survey setName(String name) {
        this.name = name;
        return this;
    }
}
