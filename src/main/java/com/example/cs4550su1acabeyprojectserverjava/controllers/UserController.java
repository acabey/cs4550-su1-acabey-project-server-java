package com.example.cs4550su1acabeyprojectserverjava.controllers;

import com.example.cs4550su1acabeyprojectserverjava.models.User;
import com.example.cs4550su1acabeyprojectserverjava.services.UserService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        allowCredentials = "true")
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/api/register")
    public User register(
            @RequestBody User user,
            HttpSession session) {
        User existingUser = service.findUserByUsername(user.getUsername());
        if(existingUser == null) {
            User currentUser = service.createUser(user);
            session.setAttribute("currentUser", currentUser);
            return currentUser;
        }
        return null;
    }

    @PostMapping("/api/profile")
    public User profile(HttpSession session) {
        User currentUser = (User)session.getAttribute("currentUser");
        return currentUser;
    }

    @PostMapping("/api/login")
    public ResponseEntity login(
            @RequestBody User user,
            HttpSession session) {
        User attemptedUser = service.findUserByUsername(user.getUsername());
        if (attemptedUser == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("User not found");
        }
        User currentUser = service.findUserByCredentials(user.getUsername(), user.getPassword());
        if (attemptedUser == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Invalid username or password");
        }
        session.setAttribute("currentUser", currentUser);
        return ResponseEntity.status(HttpStatus.OK).body(currentUser);
    }

    @PostMapping("/api/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
