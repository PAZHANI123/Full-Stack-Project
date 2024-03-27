package com.example.partypro.partypro.dto.response;

import com.example.partypro.partypro.model.enumerate.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String token;
    private Long uid;
    private String email;
    private String name;
    private String role;

}
