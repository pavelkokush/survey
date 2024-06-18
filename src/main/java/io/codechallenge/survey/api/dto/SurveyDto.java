package io.codechallenge.survey.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record SurveyDto(String id, @NotBlank String name, @Valid List<QuestionDto> questions) {
}