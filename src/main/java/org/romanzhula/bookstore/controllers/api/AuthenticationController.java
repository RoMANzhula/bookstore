package org.romanzhula.bookstore.controllers.api;

import lombok.RequiredArgsConstructor;
import org.romanzhula.bookstore.controllers.api.requests.LoginRequest;
import org.romanzhula.bookstore.controllers.api.requests.RegistrationRequest;
import org.romanzhula.bookstore.controllers.api.responses.AuthResponse;
import org.romanzhula.bookstore.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<AuthResponse> registration(
            @RequestBody RegistrationRequest registrationRequest
    ) {
        return ResponseEntity.ok(authenticationService.registration(registrationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
}
