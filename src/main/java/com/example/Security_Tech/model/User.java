package com.example.Security_Tech.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "[user]", uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String password;
    private String role;
    private String fullname;

    public User() {
    }

    public User(long id, String email, String password, String role, String fullname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
    }
}
