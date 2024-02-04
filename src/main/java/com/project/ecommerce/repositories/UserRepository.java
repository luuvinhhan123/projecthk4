package com.project.ecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.ecommerce.entities.User;
import org.springframework.stereotype.Component;


public interface UserRepository extends JpaRepository<User, Integer>{
    public Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
