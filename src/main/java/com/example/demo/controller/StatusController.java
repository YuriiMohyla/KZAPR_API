package com.example.demo.controller;

import com.example.demo.dto.StatusListResponseDto;
import com.example.demo.model.Status;
import com.example.demo.model.User;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class StatusController {
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/status/{type}")
    public StatusListResponseDto getStatusByType(@PathVariable(value = "type") String type) {
        return new StatusListResponseDto("get status",
                StatusListResponseDto.toDataList(statusRepository.getStatusByStatus(type)),true);
    }

}
