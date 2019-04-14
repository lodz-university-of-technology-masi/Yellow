package edu.pl.masi.yellow.model;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginToken {
    @JsonProperty("token")
    private String authToken;

    public LoginToken() {
        this.authToken = ":";
    }

    public LoginToken(String value) {
        this.authToken = value;
    }

    public LoginToken(String userName, String userPassword) {
        this.authToken = userName + ":" + userPassword;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    @JsonIgnore
    public String getUserName() {
        return this.authToken.split(":")[0];
    }

    @JsonIgnore
    public String getUserPassword() {
        return this.authToken.split(":")[1];
    }

    @JsonGetter("valid")
    public boolean isValid() {
        return !this.authToken.equals(":");
    }
}
