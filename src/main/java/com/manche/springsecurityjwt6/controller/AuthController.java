package com.manche.springsecurityjwt6.controller;

import com.manche.springsecurityjwt6.dto.AuthenticationRequest;
import com.manche.springsecurityjwt6.dto.AuthenticationResponse;
import com.manche.springsecurityjwt6.dto.RegisterRequest;
import com.manche.springsecurityjwt6.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest registerRequest
    ){
        return ResponseEntity.ok(authenticationService.authenticate(registerRequest));
    }
}
