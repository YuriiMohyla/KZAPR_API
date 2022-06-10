package com.example.demo.dto;

    /*title
description
startDate
endDate
creator (not required)
color (not required)
inspector (not required)
assignTo (not required)
attachments (not required)
status (not required)*/

import com.example.demo.model.Contract;
import com.example.demo.model.Project;
import com.example.demo.model.Status;
import com.example.demo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class TaskRequestDto {

    private String title;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private String color;
    private Long projectId;
    private Long parentId;
    private Long statusId;

    public Task toTask() {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setTime_start(startTime);
        task.setTime_end(endTime);
        task.setColor(color);
        return task;
    }
    public static List<TaskRequestDto> toTaskList(List<Task> taskList){
        List<TaskRequestDto> returnList = new ArrayList<>();

        for (Task task:taskList) {
            if (task.getTask() == null) returnList.add(new TaskRequestDto(
                    task.getTitle(),task.getDescription(),task.getTime_start(),task.getTime_end(),
                    task.getColor(),task.getProject().getProject_id(), 0L,task.getStatus().getStatus_id()));
                else
            returnList.add(new TaskRequestDto(
                    task.getTitle(),task.getDescription(),task.getTime_start(),task.getTime_end(),
                    task.getColor(),task.getProject().getProject_id(),task.getTask().getTask_id(),task.getStatus().getStatus_id()));
        }
        return returnList;
    }
}
