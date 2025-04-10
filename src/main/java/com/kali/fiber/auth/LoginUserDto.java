package com.kali.fiber.auth;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;

    private String password;

}