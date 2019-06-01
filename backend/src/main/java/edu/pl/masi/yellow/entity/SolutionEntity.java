package edu.pl.masi.yellow.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class SolutionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private TestEntity testEntity;

    @ManyToOne
    private PositionEntity positionEntity;

    @OneToMany
    private List<AnswerEntity> listOfAnswers;

    private String language;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }

    public PositionEntity getPositionEntity() {
        return positionEntity;
    }

    public void setPositionEntity(PositionEntity positionEntity) {
        this.positionEntity = positionEntity;
    }

    public List<AnswerEntity> getListOfAnswers() {
        return listOfAnswers;
    }

    public void setListOfAnswers(List<AnswerEntity> listOfAnswers) {
        this.listOfAnswers = listOfAnswers;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
