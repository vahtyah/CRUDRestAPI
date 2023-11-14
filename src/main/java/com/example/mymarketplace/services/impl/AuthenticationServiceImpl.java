package com.example.mymarketplace.services.impl;

import com.example.mymarketplace.dto.JwtAuthenticationResponse;
import com.example.mymarketplace.dto.RefreshTokenRequest;
import com.example.mymarketplace.dto.SignInRequest;
import com.example.mymarketplace.dto.SignUpRequest;
import com.example.mymarketplace.models.Role;
import com.example.mymarketplace.models.User;
import com.example.mymarketplace.repositories.UserRepository;
import com.example.mymarketplace.services.AuthenticationService;
import com.example.mymarketplace.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public User signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setLogin(signUpRequest.getLogin());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getLogin(),
                signInRequest.getPassword()
        ));
        var user = userRepository.findByLogin(signInRequest.getLogin()).orElseThrow(() -> new RuntimeException("User not found"));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setAccessToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshToken) {
        String userLogin = jwtService.extractUsername(refreshToken.getRefreshToken());
        User user = userRepository.findByLogin(userLogin).orElseThrow(() -> new RuntimeException("User not found"));
        if(jwtService.isTokenValid(refreshToken.getRefreshToken(), user)) {
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setAccessToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshToken.getRefreshToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }


}
