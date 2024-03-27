package com.example.partypro.partypro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.partypro.partypro.model.User;
import com.example.partypro.partypro.model.enumerate.Role;


public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);

    User findByUid(Long uid);

    void deleteByUid(Long uid);
    List<User> findByRole(Role role);

}
