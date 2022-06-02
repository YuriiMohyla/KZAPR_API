package com.example.demo.controller;

import com.example.demo.repository.RulesRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RulesController {
    @Autowired
    RulesRepository rulesRepository;

    // Получить все записи
    @GetMapping("/rules")
    public List getAllNotes() {
        return rulesRepository.findAll();
    }
}
