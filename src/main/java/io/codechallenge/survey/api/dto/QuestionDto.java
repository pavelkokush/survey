package io.codechallenge.survey.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record QuestionDto(String id, @NotBlank String text, @Valid List<AnswerDto> answers) {
}
