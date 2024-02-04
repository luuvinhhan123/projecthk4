package com.project.ecommerce.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.User;
import com.project.ecommerce.repositories.UserRepository;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User findUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    public User register() {
        return null;
    }

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public boolean delete(Integer id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

/*    public User findUserById(Integer userId) {
        User user = userRepository.findById(userId).get();
        if (user.getProfile() == null || user.getProfile().getId() == 0) {
            user.setProfile(new Profile());
        }

        return user;
    }*/

    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }

}
