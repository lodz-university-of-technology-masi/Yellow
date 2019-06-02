package edu.pl.masi.yellow.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerResponse {
    @JsonProperty("answerId")
    public int answerId;

    @JsonProperty("questionId")
    public int questionId;

    @JsonProperty("answer")
    public String answer;

    @JsonProperty("score")
    public boolean score;
}
