package edu.pl.masi.yellow.entity;

import javax.persistence.*;

@Entity
@Table(name="test_data")
public class TestEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="test_name")
    private String testname;

    @ManyToOne
    private UserEntity owner;


    public TestEntity(String testname, UserEntity owner) {
        this.testname = testname;
        this.owner = owner;
    }

    public TestEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }
}
