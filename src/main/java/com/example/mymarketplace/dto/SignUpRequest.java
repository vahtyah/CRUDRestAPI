package com.example.mymarketplace.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String login;
    private String password;
    private String name;
}
