package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/messages")
public class MessageResource {
    private final int MAX_USER_LENGTH = 15;
    private final int MAX_PASSWORD_LENGTH = 15;

    @GetMapping("/simpleMessageWelcome")
    public String simpleMessageWelcome() {
        return "WELCOME TO THE MICRO-SERVICE CLASS USING SPRING BOOT!!!";
    }

    @GetMapping("/login")
    public String login(
        @RequestParam(value = "user", defaultValue = "") String user,
        @RequestParam(value = "password", defaultValue = "") String password
    ) {
        user = user.trim();
        password = password.trim();

        if (user.isEmpty() && password.isEmpty()) {
            return "USER AND PASSWORD NOT PROVIDED";
        }

        if (user.isEmpty()) {
            return "USER NOT PROVIDED";
        }

        if (password.isEmpty()) {
            return "PASSWORD NOT PROVIDED";
        }

        if (user.length() > MAX_USER_LENGTH && password.length() > MAX_PASSWORD_LENGTH) {
            return "USER AND PASSWORD INVALID";
        }

        if (user.length() > MAX_USER_LENGTH) {
            return "INVALID USER";
        }

        if (password.length() > MAX_PASSWORD_LENGTH) {
            return "INVALID USER PASSWORD";
        }

        return "LOGIN SUCCESSFUL!!!";
    }
}