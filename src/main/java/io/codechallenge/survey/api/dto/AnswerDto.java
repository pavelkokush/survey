package io.codechallenge.survey.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AnswerDto(String id, @NotBlank String text) {
}
