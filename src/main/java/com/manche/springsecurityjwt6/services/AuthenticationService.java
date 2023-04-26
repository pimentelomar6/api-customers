package com.manche.springsecurityjwt6.services;

import com.manche.springsecurityjwt6.dto.AuthenticationRequest;
import com.manche.springsecurityjwt6.dto.AuthenticationResponse;
import com.manche.springsecurityjwt6.dto.RegisterRequest;
import com.manche.springsecurityjwt6.model.Role;
import com.manche.springsecurityjwt6.model.User;
import com.manche.springsecurityjwt6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .mail(registerRequest.getMail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest registerRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.getMail(),
                        registerRequest.getPassword()
                )
        );

        User user = userRepository.findByMail(registerRequest.getMail()).orElseThrow();

        String jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();


    }
}
