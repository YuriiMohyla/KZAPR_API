package com.example.demo.controller;

import com.example.demo.dto.UserResponseDto;
import com.example.demo.model.User;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileRepository profileRepository;

    @GetMapping("/admin/users")
    public UserResponseDto getAllUsers() {
        return new UserResponseDto("get all users",UserResponseDto.fromProfileList(profileRepository.findAll()),true);
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
