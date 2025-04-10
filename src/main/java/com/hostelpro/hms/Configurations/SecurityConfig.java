package com.hostelpro.hms.Configurations;

import com.hostelpro.hms.Services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .anyRequest().permitAll()

                        // implementation for RBAC (has error now)
//                                .requestMatchers("/auth/**", "/register", "/login", "/css", "/js", "/images/**", "/v3/api-docs/**",
//                                        "/swagger-ui/**",
//                                        "/swagger-ui.html", "/api/student/register").permitAll()
//                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
//                                .requestMatchers("/api/warden/**").hasRole("WARDEN")
//                                .requestMatchers("/api/student/**").hasRole("STUDENT")
//                                .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable())
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/auth/login")
//                        .successHandler(customLoginSuccessHandler)
//                        .permitAll()
//                )
                .logout(form -> form

                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder, CustomUserDetailService customUserDetailService) throws Exception {
        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder);
        return builder.build();
    }
}
