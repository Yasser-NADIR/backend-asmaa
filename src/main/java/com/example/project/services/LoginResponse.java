package com.example.project.services;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiresIn;
    public String getToken() {
        return token;
    }
}
