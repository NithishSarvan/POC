package com.nithish.fiber.auth;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;

    private String password;

    private String fullName;

    // getters and setters here...
}