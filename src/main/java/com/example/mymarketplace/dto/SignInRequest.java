package com.example.mymarketplace.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String login;
    private String password;
}
