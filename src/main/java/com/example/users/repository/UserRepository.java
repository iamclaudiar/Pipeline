package com.example.users.repository;

import com.example.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    org.springframework.security.core.userdetails.User findByUsername(String username);
}
