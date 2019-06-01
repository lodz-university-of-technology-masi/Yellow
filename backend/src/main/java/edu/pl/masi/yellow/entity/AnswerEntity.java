package edu.pl.masi.yellow.entity;

import javax.persistence.*;

public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    QuestionEntity questionEntity;

    String answer;

    public AnswerEntity() {
    }

    public AnswerEntity(QuestionEntity questionEntity, String answer) {
        this.questionEntity = questionEntity;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
