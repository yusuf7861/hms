package com.hostelpro.hms.Services.ServiceImplementation;

import com.hostelpro.hms.DTOs.Requests.LoginRequest;
import com.hostelpro.hms.DTOs.Requests.RegisterRequest;
import com.hostelpro.hms.Entities.Enum.Role;
import com.hostelpro.hms.Entities.User;
import com.hostelpro.hms.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public void register(RegisterRequest registerRequest)
    {
        Optional<User> existing = userRepository.findByEmail(registerRequest.email());
        if (existing.isPresent()) {
            throw new RuntimeException("User already exists with this email.");
        }

        User user = new User();
        user.setEmail(registerRequest.email());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setRole(Role.STUDENT);

        userRepository.save(user);
    }

    public void login(LoginRequest request)
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
        } catch (Exception e)
        {
            throw new RuntimeException("Invalid email or password.");
        }
    }
}
