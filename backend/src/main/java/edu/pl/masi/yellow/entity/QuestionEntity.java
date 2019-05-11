package edu.pl.masi.yellow.entity;

import javax.persistence.*;

@Entity
@Table(name="question_data")
public class QuestionEntity {
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
    private String type;

    @Column(name = "question_meta")
    private String metadata;

    public QuestionEntity(int number, String language,
                          String description, String type,
                          String metadata) {
        this.number = number;
        this.language = language;
        this.description = description;
        this.type = type;
        this.metadata = metadata;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
