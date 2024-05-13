package com.evilapp.fire.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evilapp.fire.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
