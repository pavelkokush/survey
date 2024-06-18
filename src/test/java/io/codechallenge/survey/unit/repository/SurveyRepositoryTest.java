package io.codechallenge.survey.unit.repository;

import io.codechallenge.survey.repository.SurveyRepository;
import io.codechallenge.survey.service.model.Question;
import io.codechallenge.survey.service.model.Answer;
import io.codechallenge.survey.service.model.Survey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

@DataJpaTest
public class SurveyRepositoryTest {

    @Autowired
    private SurveyRepository surveyRepository;

    @Test
    void expectSuccessfulSurveyCreation() {
        //given
        var survey = new Survey()
                .setName("Survey A")
                .setQuestions(List.of(new Question()
                                .setText("Question A1")
                                .setAnswers(List.of(new Answer().setText("Option 1"))
                                )
                        )
                );

        //when
        var savedSurvey = surveyRepository.save(survey);

        //then
        assertThat(savedSurvey.id(), notNullValue());
        assertThat(savedSurvey.name(), is(survey.name()));
        assertThat(savedSurvey.questions(), hasSize(survey.questions().size()));

        assertThat(savedSurvey.questions().get(0).id(), notNullValue());
        assertThat(savedSurvey.questions().get(0).text(), is(survey.questions().get(0).text()));
        assertThat(savedSurvey.questions().get(0).answers(), hasSize(survey.questions().get(0).answers().size()));

        assertThat(savedSurvey.questions().get(0).answers().get(0).id(), notNullValue());
        assertThat(savedSurvey.questions().get(0).answers().get(0).text(), is(survey.questions().get(0).answers().get(0).text()));
    }
}
