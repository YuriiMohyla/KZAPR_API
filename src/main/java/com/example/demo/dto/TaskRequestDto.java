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

}

/*{
    "title" : "f",
    "description" : "f",
    "startTime" : 100000000,
    "endTime" : 1000000000,
    "color" : "a",
    "projectId" : 2,
    "parentId" : 1,
    "statusId" : 2
}*/
