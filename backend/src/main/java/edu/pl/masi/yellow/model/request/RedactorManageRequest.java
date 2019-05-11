package edu.pl.masi.yellow.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RedactorManageRequest {
    @JsonProperty("user_id")
    private int id;

    public RedactorManageRequest() {

    }

    public RedactorManageRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
