package com.example.partypro.partypro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.partypro.partypro.constant.Api;
import com.example.partypro.partypro.dto.request.RegisterRequest;
import com.example.partypro.partypro.dto.request.UserRequest;
import com.example.partypro.partypro.dto.response.UserResponse;
import com.example.partypro.partypro.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(Api.AUTH)
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthencationController {
     private final AuthService authService ;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        boolean isRegistered = authService.userRegistration(request);
        return isRegistered ? ResponseEntity.ok("User registered successfully")
                : ResponseEntity.badRequest().body("Sommething went wrong!");
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> authenticate(@RequestBody UserRequest request) {

        return ResponseEntity.ok(authService.userAuthentication(request));
    }
}
