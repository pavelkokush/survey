CREATE TABLE IF NOT EXISTS surveys
(
    id   BIGINT,
    name VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS questions
(
    id        BIGINT,
    survey_id BIGINT,
    text      VARCHAR(1000) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (survey_id) REFERENCES surveys (id)
);

CREATE TABLE IF NOT EXISTS answers
(
    id          BIGINT,
    text        VARCHAR(1000) NOT NULL,
    question_id BIGINT,

    PRIMARY KEY (id),
    FOREIGN KEY (question_id) REFERENCES questions (id)
);