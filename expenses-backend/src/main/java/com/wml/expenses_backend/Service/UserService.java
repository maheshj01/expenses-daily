package com.wml.expenses_backend.Service;

import com.wml.expenses_backend.Entity.User;
import com.wml.expenses_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wml.expenses_backend.dto.UserDTO;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setGoogleId(userDTO.getGoogleId());
        user.setAuthProvider(userDTO.getAuthProvider());
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId);
    }
}
