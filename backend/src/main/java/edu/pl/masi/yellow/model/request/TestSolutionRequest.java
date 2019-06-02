package edu.pl.masi.yellow.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestSolutionRequest {
    @JsonProperty("testId")
    public int testId;

    @JsonProperty("positionId")
    public int positionId;

    @JsonProperty("language")
    public String language;

    @JsonProperty("answers")
    public List<Answer> answerList;
}
