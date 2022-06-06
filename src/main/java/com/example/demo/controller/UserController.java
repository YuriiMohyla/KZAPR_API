package com.example.demo.controller;

import com.example.demo.dto.ProfileDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.model.User;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return new UserResponseDto("get all users", UserResponseDto.fromProfileList(profileRepository.findAll()), true);
    }

    @PostMapping("/user")
    public User createNote(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/admin/users/{id}")
    public ProfileDto getUserById(@PathVariable(value = "id") Long user_id) {
        return new ProfileDto(ProfileDto.fromUser(userRepository.getById(user_id)));
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity deletUserById(@PathVariable(value = "id") Long user_id) {
        if (userRepository.findById(user_id).isPresent()) {
            userRepository.deleteById(user_id);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }
}
