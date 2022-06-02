package com.example.demo.controller;

import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class ContractController {

    @Autowired
    ContractRepository contractRepository;

    // Получить все записи
    @GetMapping("/contract/{id}")
    public List getAllNotes() {
        return contractRepository.findAll();
    }
}
