package com.example.Security_Tech.userDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String email;
    private String password;
    private String role;
    private String fullname;

    public UserDTO() {
    }

    public UserDTO(String email, String password, String role, String fullname) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
    }


}
