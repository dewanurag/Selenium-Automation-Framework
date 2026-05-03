package com.ui.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Environment {

    @JsonProperty("URL")   // ← tells Jackson to use "URL" not "url"
    private String URL;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}