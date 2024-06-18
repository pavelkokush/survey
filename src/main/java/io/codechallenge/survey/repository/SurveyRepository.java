package io.codechallenge.survey.repository;

import io.codechallenge.survey.service.model.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
}
