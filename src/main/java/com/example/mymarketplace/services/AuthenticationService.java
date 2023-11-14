package com.example.mymarketplace.services;

import com.example.mymarketplace.dto.JwtAuthenticationResponse;
import com.example.mymarketplace.dto.RefreshTokenRequest;
import com.example.mymarketplace.dto.SignInRequest;
import com.example.mymarketplace.dto.SignUpRequest;
import com.example.mymarketplace.models.Role;
import com.example.mymarketplace.models.User;

public interface AuthenticationService {
    public User signup(SignUpRequest signUpRequest);
    public JwtAuthenticationResponse signin(SignInRequest signInRequest);
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);
}
