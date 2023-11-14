package com.example.mymarketplace.controllers;

import com.example.mymarketplace.dto.JwtAuthenticationResponse;
import com.example.mymarketplace.dto.RefreshTokenRequest;
import com.example.mymarketplace.dto.SignInRequest;
import com.example.mymarketplace.dto.SignUpRequest;
import com.example.mymarketplace.models.Role;
import com.example.mymarketplace.models.User;
import com.example.mymarketplace.repositories.UserRepository;
import com.example.mymarketplace.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }

    @PutMapping("/update_role_admin/{id}")
    public ResponseEntity<User> updateUserRoleAdmin(@PathVariable Long id) {
        User userFromDb = userRepository.findById(id).get();
        userFromDb.setRole(Role.ADMINISTRATOR);
        return ResponseEntity.ok(userRepository.save(userFromDb));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAllUsers() {
            userRepository.deleteAll();
            return ResponseEntity.ok("Users deleted");
    }


}
