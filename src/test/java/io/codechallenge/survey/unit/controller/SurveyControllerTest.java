package io.codechallenge.survey.unit.controller;

import io.codechallenge.survey.service.SurveyService;
import io.codechallenge.survey.service.model.Answer;
import io.codechallenge.survey.service.model.Question;
import io.codechallenge.survey.service.model.Survey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SurveyControllerTest {
    private static final String BASE_URL = "/api/surveys";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Test
    public void expectSuccessfulSurveyCreation() throws Exception {
        //given
        var survey = new Survey()
                .setId(1L)
                .setName("Survey A")
                .setQuestions(List.of(new Question()
                                .setId(2L)
                                .setText("Question A1")
                                .setAnswers(List.of(new Answer().setId(3L).setText("Option 1"))
                                )
                        )
                );
        when(surveyService.create(any(Survey.class))).thenReturn(survey);

        //when-then
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Survey A",
                                  "questions": [
                                    {
                                      "text": "Question A1",
                                      "answers": [
                                        {
                                          "text": "Option 1"
                                        }
                                      ]
                                    }
                                  ]
                                }
                                """)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(survey.id().toString())));

        verify(surveyService, times(1)).create(argThat(
                argument -> argument.id() == null &&
                        argument.name().equals(survey.name()) &&
                        argument.questions().size() == survey.questions().size()));
    }

    @Test
    public void expectValidationErrorsOnIncorrectSurveyCreation() throws Exception {
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                 {
                                 }
                                """)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name", is("must not be blank")));

        verify(surveyService, never()).create(any(Survey.class));
    }
}
