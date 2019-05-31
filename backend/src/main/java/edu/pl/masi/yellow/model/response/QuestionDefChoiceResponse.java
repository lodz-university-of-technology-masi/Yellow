package edu.pl.masi.yellow.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pl.masi.yellow.entity.QuestionEntity;

import java.util.Arrays;
import java.util.List;

public class QuestionDefChoiceResponse extends QuestionDefResponse {
    @JsonProperty("choices")
    private List<String> choices;

    public QuestionDefChoiceResponse(QuestionEntity question) {
        super(question);

        this.choices = Arrays.asList(question.getMetadata().split("\\|"));
    }
}
