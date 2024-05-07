package com.example.Security_Tech.service;

import com.example.Security_Tech.model.User;
import com.example.Security_Tech.userDTO.UserDTO;

public interface UserService {
    User save(UserDTO userDTO);
}
