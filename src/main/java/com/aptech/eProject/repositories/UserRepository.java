package com.aptech.eProject.repositories;

import com.aptech.eProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
