package com.example.cs4550su1acabeyprojectserverjava.controllers;

import com.example.cs4550su1acabeyprojectserverjava.models.Watchlist;
import com.example.cs4550su1acabeyprojectserverjava.utilities.APIErrorSchema;
import com.example.cs4550su1acabeyprojectserverjava.models.User;
import com.example.cs4550su1acabeyprojectserverjava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
//@CrossOrigin(
//        origins = "http://acabey-project-server-java.herokuapp.com",
//        allowedHeaders = "*",
//        allowCredentials = "true")
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/api/register")
    public ResponseEntity register(
            @RequestBody User user,
            HttpSession session) {
        User existingUser = service.findUserByUsername(user.getUsername());
        if(existingUser == null) {
            User currentUser = service.createUser(user);
            session.setAttribute("currentUser", currentUser);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(currentUser);
        }
        return new ResponseEntity(new APIErrorSchema("User exists"), HttpStatus.FORBIDDEN);
    }

    @GetMapping("/api/profile")
    public User profile(HttpSession session) {
        User currentUser = (User)session.getAttribute("currentUser");
        return currentUser;
    }


    @GetMapping("/api/profile/{username}")
    public User profileByUsername(@PathVariable String username, HttpSession session) {
        User currentUser = (User)session.getAttribute("currentUser");
        User requestedUser = service.findUserByUsername(username);

        if (currentUser.getId().equals(requestedUser.getId()) || currentUser.getRole().equals("ADMIN")) {
            return requestedUser;
        } else {
            return requestedUser.anonymize();
        }
    }


    @GetMapping("/api/users/{userId}")
    public User profileByUserId(@PathVariable Integer userId, HttpSession session) {
        User currentUser = (User)session.getAttribute("currentUser");
        User requestedUser = service.findUserById(userId);

        if (currentUser != null && (currentUser.getId().equals(requestedUser.getId()) || currentUser.getRole().equals("ADMIN"))) {
            return requestedUser;
        } else {
            return requestedUser.anonymize();
        }
    }

    @PutMapping("/api/profile/{username}")
    public ResponseEntity updateProfile(
            @PathVariable String username,
            @RequestBody User user,
            HttpSession session) {

        User attemptedUser = service.findUserByUsername(username);
        User currentUser = (User)session.getAttribute("currentUser");
        if (attemptedUser == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("User not found");
        }
        else if (currentUser == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new APIErrorSchema("Cannot update profile as anonymous user"));
        }
        else if (!currentUser.getRole().equals("ADMIN") && !currentUser.getId().equals(attemptedUser.getId())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new APIErrorSchema("Inadequate permissions to update profile"));
        } else {
            User updatedUser = service.updateUser(username, user);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        }

    }

    @DeleteMapping("/api/profile/{username}")
    public ResponseEntity deleteUser(
            @PathVariable String username,
            HttpSession session) {
        User attemptedUser = service.findUserByUsername(username);
        User currentUser = (User)session.getAttribute("currentUser");

        if (attemptedUser == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new APIErrorSchema("User not found"));
        }
        else if (currentUser == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new APIErrorSchema("Cannot delete profile as anonymous user"));
        }
        else if (!currentUser.getRole().equals("ADMIN") && !currentUser.getId().equals(attemptedUser.getId())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new APIErrorSchema("Inadequate permissions to delete profile"));
        } else {
            Integer deletedUser = service.deleteUser(username);
            return ResponseEntity.status(HttpStatus.OK).body(deletedUser);
        }

    }


    @PostMapping("/api/login")
    public ResponseEntity login(
            @RequestBody User user,
            HttpSession session) {
        User attemptedUser = service.findUserByUsername(user.getUsername());
        if (attemptedUser == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new APIErrorSchema("User not found"));
        }
        User currentUser = service.findUserByCredentials(user.getUsername(), user.getPassword());
        if (currentUser == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new APIErrorSchema("Invalid username or password"));
        }
        session.setAttribute("currentUser", currentUser);
        return ResponseEntity.status(HttpStatus.OK).body(currentUser);
    }

    @PostMapping("/api/logout")
    public Integer logout(HttpSession session) {
        session.invalidate();
        return 1;
    }
}
