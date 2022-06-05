package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class TaskPerWeekDto {
    private String message = "completed tasks per a day";
    private List<TaskPerWeek> data;
    private boolean success = true;

    public TaskPerWeekDto(List<TaskPerWeek> data) {
        this.data = data;
    }

    @AllArgsConstructor
    @Getter
    public static class TaskPerWeek {
        private String dayOfWeek;
        private int taskAmount;
    }
}
