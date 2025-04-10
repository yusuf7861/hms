package com.hostelpro.hms.Controllers;

import com.hostelpro.hms.DTOs.Requests.LoginRequest;
import com.hostelpro.hms.DTOs.Requests.RegisterRequest;
import com.hostelpro.hms.Services.ServiceImplementation.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@RestController("/auth")
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

        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/login?logout=true")
    public ResponseEntity<?> logoutSuccess() {
        return ResponseEntity.ok("Logout successful");
    }

}
