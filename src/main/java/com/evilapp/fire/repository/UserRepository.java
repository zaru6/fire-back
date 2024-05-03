package com.evilapp.fire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evilapp.fire.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
