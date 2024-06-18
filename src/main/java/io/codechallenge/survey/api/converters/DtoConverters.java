package io.codechallenge.survey.api.converters;

import io.codechallenge.survey.api.dto.AnswerDto;
import io.codechallenge.survey.api.dto.QuestionDto;
import io.codechallenge.survey.api.dto.SurveyDto;
import io.codechallenge.survey.service.model.Answer;
import io.codechallenge.survey.service.model.Question;
import io.codechallenge.survey.service.model.Survey;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class DtoConverters {
    public static SurveyDto toSurveyDto(Survey survey) {
        return new SurveyDto(survey.id().toString(), survey.name(), toQuestionsDto(survey.questions()));
    }

    private static List<QuestionDto> toQuestionsDto(List<Question> questions) {
        return questions
                .stream()
                .map(question -> new QuestionDto(question.id().toString(), question.text(),
                        toAnsersDto(question.answers())))
                .collect(toList());
    }

    private static List<AnswerDto> toAnsersDto(List<Answer> answers) {
        return answers
                .stream()
                .map(answer -> new AnswerDto(answer.id().toString(), answer.text()))
                .collect(toList());
    }

    public static Survey toSurvey(SurveyDto surveyDto) {
        return new Survey()
                .setName(surveyDto.name())
                .setQuestions(toQuestions(surveyDto.questions()));
    }

    private static List<Question> toQuestions(List<QuestionDto> questions) {
        return questions
                .stream()
                .map(questionDto -> new Question()
                        .setText(questionDto.text())
                        .setAnswers(toAnswers(questionDto.answers())))
                .collect(toList());
    }

    private static List<Answer> toAnswers(List<AnswerDto> answerDtos) {
        return answerDtos
                .stream()
                .map(answerDto -> new Answer().setText(answerDto.text()))
                .collect(toList());
    }

}
