package com.example.demo.controller;

import com.example.demo.exeption.UserNotFoundException;
import com.example.demo.model.Project;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin/users")
    public List getAllNotes() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User createNote(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/admin/users/{id}")
    public Optional<User> getProjectById(@PathVariable(value = "id") Long user_id) {
        return userRepository.findById(user_id);
    }
}
