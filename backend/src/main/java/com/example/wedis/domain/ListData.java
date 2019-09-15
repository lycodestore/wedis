package com.example.wedis.domain;

import java.util.ArrayList;
import java.util.Map;

public class ListData {
    private String key;
    private Long expiration;
    private ArrayList<Map> value;

    public ListData(String key, Long expiration, ArrayList<Map> value) {
        this.key = key;
        this.expiration = expiration;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public ArrayList<Map> getValue() {
        return value;
    }

    public void setValue(ArrayList<Map> value) {
        this.value = value;
    }
}
