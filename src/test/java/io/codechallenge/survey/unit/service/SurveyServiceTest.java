package io.codechallenge.survey.unit.service;

import io.codechallenge.survey.repository.SurveyRepository;
import io.codechallenge.survey.service.SurveyService;
import io.codechallenge.survey.service.model.Question;
import io.codechallenge.survey.service.model.Answer;
import io.codechallenge.survey.service.model.Survey;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SurveyServiceTest {

    @MockBean
    private SurveyRepository surveyRepository;

    @Test
    void expectSuccessfulSurveyCreation() {
        //given
        var surveyService = new SurveyService(surveyRepository);
        var survey = new Survey()
                .setName("Survey A")
                .setQuestions(List.of(new Question()
                                .setText("Question A1")
                                .setAnswers(List.of(new Answer().setText("Option 1"))
                                )
                        )
                );

        when(surveyRepository.save(survey)).thenReturn(survey);

        //when
        var savedSurvey = surveyService.create(survey);

        //then
        assertThat(savedSurvey, is(survey));
    }
}
