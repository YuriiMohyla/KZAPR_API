package com.example.demo.controller;

import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    // Получить все записи
    @GetMapping("/task")
    public List getAllNotes() {
        return taskRepository.findAll();
    }

}