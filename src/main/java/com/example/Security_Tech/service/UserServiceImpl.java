package com.example.Security_Tech.service;

import com.example.Security_Tech.model.User;
import com.example.Security_Tech.repository.UserRepository;
import com.example.Security_Tech.userDTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        System.out.println(userDTO.getPassword());
        user.setFullname(userDTO.getFullname());
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }
}
