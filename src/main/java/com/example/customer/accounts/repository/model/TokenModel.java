package com.example.customer.accounts.repository.model;

import java.io.Serializable;

public class TokenModel implements Serializable {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenModel(String token) {
        this.token = token;
    }

    public TokenModel() {
    }
}
