package com.hostelpro.hms.controllers;

import com.hostelpro.hms.dto.Requests.LoginRequest;
import com.hostelpro.hms.dto.Requests.RegisterRequest;
import com.hostelpro.hms.services.serviceimpl.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authManager;


    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authManager = authenticationManager;
    }

    @PostMapping ("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest registerRequest)
    {
        try {
            authService.register(registerRequest);
            return ResponseEntity.ok("User registered successfully.");
        } catch (Exception e) {
//            e.printStackTrace(); // show in logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());

        Authentication authentication = authManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());


        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("UNKNOWN");

        // creating response dto
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("userRole", role.replace("ROLE_", "")); // So frontend gets STUDENT, WARDEN, etc.
        response.put("message", "Login successful");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // Destroys the session
        return ResponseEntity.ok("User logged out successfully.");
    }
}
