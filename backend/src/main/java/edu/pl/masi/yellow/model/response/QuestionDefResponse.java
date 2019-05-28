package edu.pl.masi.yellow.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pl.masi.yellow.entity.QuestionEntity;

public class QuestionDefResponse {
    @JsonProperty("questionNumber")
    private int questionNumber;

    @JsonProperty("questionDesc")
    private String questionDesc;

    @JsonProperty("questionLang")
    private String questionLang;

    @JsonProperty("questionType")
    private QuestionEntity.QuestionType type;


    public QuestionDefResponse(QuestionEntity entity) {
        this.questionNumber = entity.getNumber();
        this.questionDesc = entity.getDescription();
        this.questionLang = entity.getLanguage();
        this.type = entity.getType();
    }
}
