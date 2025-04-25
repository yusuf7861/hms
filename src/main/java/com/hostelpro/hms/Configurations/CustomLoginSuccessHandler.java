package com.hostelpro.hms.configurations;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<?extends GrantedAuthority> authorities = authentication.getAuthorities();
        for(GrantedAuthority auth : authorities) {
            String role = auth.getAuthority();
            switch (role) {
                case "ADMIN" -> {
                    response.sendRedirect("/api/admin/dashboard");
                    return;
                }
                case "WARDEN" -> {
                    response.sendRedirect("/api/warden/dashboard");
                    return;
                }
                case "STUDENT" -> {
                    response.sendRedirect("/api/student/dashboard");
                    return;
                }
            }
            response.sendRedirect("/home");
        }
    }
}
