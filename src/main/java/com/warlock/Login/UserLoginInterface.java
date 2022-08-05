package com.warlock.Login;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginInterface extends JpaRepository<UserLogin,Long> {
    Optional<UserLogin> findByEmail(String email);
}
