package com.example.cs4550su1acabeyprojectserverjava.services;

import com.example.cs4550su1acabeyprojectserverjava.models.User;
import com.example.cs4550su1acabeyprojectserverjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public User createUser(User user) {
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        return repository.save(user);
    }

    public User updateUser(String username, User updatedUser) {
        User user = repository.findUserByUsername(username);

        updatedUser.setId(user.getId());
        user.copyUser(updatedUser);
        user.setUsername(username);

        try {
            repository.save(user);
            return user;
        } catch (AssertionError e) {
            return null;
        }
    }

    public User findUserById(Integer id) {
        return repository.findUserById(id);
    }

    public User findUserByCredentials(String username, String password) {
        return repository.findUserByCredentials(username, password);
    }

    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }
}
