package edu.pl.masi.yellow.entity;

import edu.pl.masi.yellow.model.request.QuestionAddRequest;

import javax.persistence.*;

@Entity
@Table(name="question_data")
public class QuestionEntity {
    public enum QuestionType {
        OPEN,
        NUMBER,
        SCALE,
        CHOICE
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question_number")
    private int number;

    @Column(name = "question_language")
    private String language;

    @Column(name = "question_desc")
    private String description;

    @Column(name = "question_type")
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Column(name = "question_meta")
    private String metadata;

    @ManyToOne
    private TestEntity test;



    public QuestionEntity(int number, String language,
                          String description, QuestionType type,
                          String metadata, TestEntity test) {
        this.number = number;
        this.language = language;
        this.description = description;
        this.type = type;
        this.metadata = metadata;
        this.test = test;
    }

    public QuestionEntity(QuestionAddRequest request) {
        this.number = request.getQuestionNumber();
        this.language = request.getQuestionLang();
        this.description = request.getQuestionDesc();
        this.type = request.getType();
        this.metadata = request.getQuestionData();

    }

    public QuestionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public TestEntity getTest() {
        return test;
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "" + this.getNumber() + ";" + this.getTypeToString() + ";" + this.getLanguage()
                + ";" + this.getDescription() + ";";
    }

    private String getTypeToString() {
        switch(this.getType()) {
            case SCALE:
                return "S";
            case CHOICE:
                return "W";
            case NUMBER:
                return "L";
            case OPEN:
                return "O";
        }
        return "";
    }
}
