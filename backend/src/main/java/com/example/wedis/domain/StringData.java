package com.example.wedis.domain;

public class StringData {
    private String key;
    private String value;
    private Long expiration;

    public StringData(String key, String value, Long expiration) {
        this.key = key;
        this.value = value;
        this.expiration = expiration;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}
