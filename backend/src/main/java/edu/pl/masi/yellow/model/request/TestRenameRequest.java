package edu.pl.masi.yellow.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestRenameRequest {
    @JsonProperty("testId")
    private int id;

    @JsonProperty("newName")
    private String newName;

    public TestRenameRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
