package org.romanzhula.bookstore.services;

import lombok.RequiredArgsConstructor;
import org.romanzhula.bookstore.controllers.api.requests.LoginRequest;
import org.romanzhula.bookstore.controllers.api.requests.RegistrationRequest;
import org.romanzhula.bookstore.controllers.api.responses.AuthResponse;
import org.romanzhula.bookstore.models.User;
import org.romanzhula.bookstore.models.UserRole;
import org.romanzhula.bookstore.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse registration(RegistrationRequest registrationRequest) {
        var newUser = User.builder()
                .username(registrationRequest.getUsername())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .role(UserRole.USER)
                .build()
        ;

        userRepository.save(newUser);

        var jwtToken = jwtService.generateToken(newUser);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse login(LoginRequest registrationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registrationRequest.getUsername(),
                        registrationRequest.getPassword()
                )
        );

        var logUser = userRepository.findUserByUsername(registrationRequest.getUsername()).orElseThrow();

        var jwtToken = jwtService.generateToken(logUser);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

}
