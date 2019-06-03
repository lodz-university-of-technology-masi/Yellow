package edu.pl.masi.yellow.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TranslationRequest {
    @JsonProperty("testId")
    public int testId;

    @JsonProperty("from")
    public String fromLang;

    @JsonProperty("to")
    public String toLang;
}
