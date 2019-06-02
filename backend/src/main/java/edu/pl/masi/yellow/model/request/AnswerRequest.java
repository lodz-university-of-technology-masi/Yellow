package edu.pl.masi.yellow.model.request;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerRequest {
    public AnswerRequest() {}

    @JsonProperty("questionId")
    public int questionId;

    @JsonProperty("answer")
    public String answerString;
}
