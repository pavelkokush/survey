package io.codechallenge.survey.integration;

import io.codechallenge.survey.service.model.Survey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyApiIntegrationTest {
    private static final String BASE_URL = "/api/surveys";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void expectSuccessfulSurveyCreation() {
        //given
        var creationRequest = new HttpEntity<>("""
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
                """, httpHeaders());

        //when
        var survey = restTemplate.postForObject(BASE_URL, creationRequest, Survey.class);

        //then
        assertThat(survey.id(), notNullValue());
        assertThat(survey.name(), is("Survey A"));
        assertThat(survey.questions(), hasSize(1));

        assertThat(survey.questions().get(0).id(), notNullValue());
        assertThat(survey.questions().get(0).text(), is("Question A1"));
        assertThat(survey.questions().get(0).answers(), hasSize(1));

        assertThat(survey.questions().get(0).answers().get(0).id(), notNullValue());
        assertThat(survey.questions().get(0).answers().get(0).text(), is("Option 1"));
    }


    private static HttpHeaders httpHeaders() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
