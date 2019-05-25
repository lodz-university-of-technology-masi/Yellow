package edu.pl.masi.yellow.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pl.masi.yellow.entity.QuestionEntity;

public class QuestionAddRequest {
    @JsonProperty("questionNumber")
    private int questionNumber;

    @JsonProperty("questionDesc")
    private String questionDesc;

    @JsonProperty("questionLang")
    private String questionLang;

    @JsonProperty("questionType")
    private QuestionEntity.QuestionType type;

    @JsonProperty("questionData")
    private String questionData;

    public QuestionAddRequest() {
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public String getQuestionLang() {
        return questionLang;
    }

    public void setQuestionLang(String questionLang) {
        this.questionLang = questionLang;
    }

    public QuestionEntity.QuestionType getType() {
        return type;
    }

    public void setType(QuestionEntity.QuestionType type) {
        this.type = type;
    }

    public String getQuestionData() {
        return questionData;
    }

    public void setQuestionData(String questionData) {
        this.questionData = questionData;
    }
}
