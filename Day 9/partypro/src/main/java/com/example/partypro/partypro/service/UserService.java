package com.example.partypro.partypro.service;

import java.util.List;

import com.example.partypro.partypro.dto.request.UserRequest;
import com.example.partypro.partypro.dto.response.UserResponse;
import com.example.partypro.partypro.model.enumerate.Role;

public interface UserService {
     List<UserResponse> getAllUsers();

    UserResponse getUser(Long uid);

    UserResponse updateUser(UserRequest request, Long uid);

    boolean deleteUser(Long uid);

    public List<UserResponse> getAdminUsers();

    public List<UserResponse> getCustomerUsers();

    public Role getUserRole(Long uid);
}
