package edu.pl.masi.yellow.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="position_data")
public class PositionEntity {
    @JsonProperty("positionId")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "position_desc")
    @JsonProperty("positionName")
    private String positionName;

    @Column(name = "position_active")
    @JsonProperty("isActive")
    private boolean isActive;

    @ManyToMany
    @JsonIgnore
    private List<TestEntity> testEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<TestEntity> getTestEntities() {
        return testEntities;
    }

    public void setTestEntities(List<TestEntity> testEntities) {
        this.testEntities = testEntities;
    }

    public void addTest(TestEntity testEntity) {
        this.testEntities.add(testEntity);
    }

    public void removeTest(TestEntity testEntity) {
        this.testEntities.remove(testEntity);
    }

    @JsonGetter("tests")
    public List<Integer> getTestId() {
        if (this.testEntities != null) {
            return this.testEntities.stream()
                    .map(TestEntity::getId)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
