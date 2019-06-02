package edu.pl.masi.yellow.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestSolutionResponse {
    @JsonProperty("solutionId")
    public int id;

    @JsonProperty("testId")
    public int testId;

    @JsonProperty("positionId")
    public int positionId;

    @JsonProperty("language")
    public String language;

    @JsonProperty("answers")
    public List<AnswerResponse> answerList;
}
