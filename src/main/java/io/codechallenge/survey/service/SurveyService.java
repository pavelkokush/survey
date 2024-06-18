package io.codechallenge.survey.service;

import io.codechallenge.survey.repository.SurveyRepository;
import io.codechallenge.survey.service.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Survey create(Survey survey) {
        return surveyRepository.save(survey);
    }
}
