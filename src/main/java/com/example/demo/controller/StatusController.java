package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public class StatusController {
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/status/{type}")
    public Optional<User> getProjectById(@PathVariable(value = "type") Long user_id) {
        return null;
    }
}
