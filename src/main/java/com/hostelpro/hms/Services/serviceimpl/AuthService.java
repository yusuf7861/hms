package com.hostelpro.hms.services.serviceimpl;

import com.hostelpro.hms.dto.Requests.LoginRequest;
import com.hostelpro.hms.dto.Requests.RegisterRequest;
import com.hostelpro.hms.entities.Enum.Role;
import com.hostelpro.hms.entities.User;
import com.hostelpro.hms.repositories.UserRepository;
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
