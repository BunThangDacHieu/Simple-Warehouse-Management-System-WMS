package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.bussinessObject.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByPhone(String phone);

    Long countUserByRole(User.Role role);

    public User findByResetPasswordToken(String token);
}
