package com.nithish.fiber.auth;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;

    private String password;

}