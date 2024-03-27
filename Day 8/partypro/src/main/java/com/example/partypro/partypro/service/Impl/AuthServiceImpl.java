package com.example.partypro.partypro.service.Impl;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.partypro.partypro.dto.request.RegisterRequest;
import com.example.partypro.partypro.dto.request.UserRequest;
import com.example.partypro.partypro.dto.response.UserResponse;
import com.example.partypro.partypro.model.User;
import com.example.partypro.partypro.model.enumerate.Role;
import com.example.partypro.partypro.repository.UserRepository;
import com.example.partypro.partypro.service.AuthService;
import com.example.partypro.partypro.util.JwtUtil;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public boolean userRegistration(RegisterRequest request) {
        Optional<User> isUserExists = userRepository.findByEmail(request.getEmail());
        if (!isUserExists.isPresent()) {
            var user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .isEnabled(true)
                    .role(Role.valueOf(request.getRole().toUpperCase()))
                    .build();
            userRepository.save(user);
            return true;
        } else {
            System.out.println("User Exists in this email");
            return false;
        }
    }

    @Override
    public UserResponse userAuthentication(UserRequest request) {
         authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var token = jwtUtil.generateToken(user);
        return UserResponse.builder()
                .token(token)
                .uid(user.getUid())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().toString())
                .build();
    }

    @Override
    public boolean adminRegistration(RegisterRequest request) {
        return true;
    }
    
}
