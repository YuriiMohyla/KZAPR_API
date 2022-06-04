package com.example.demo.dto;

import com.example.demo.model.Contract;
import com.example.demo.model.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class ListProjectsOfContractDto {
/*    {
        "message": "projects for the contract",
            "data": [
        {
            "projectId": 1,
                "title": "",
                "start": "",
                "end": ""
        },
    ...
  ],
        "success": true
    }*/
private String massage;
    private List<Data> data;
    private boolean success;

    @AllArgsConstructor
    @Getter
    @ToString
    public static class Data {
        private Long projectId;
        private String title;
        private Timestamp start;
        private Timestamp end;
    }

    public static List<Data> toDataList(List<Project> projectList) {
        List<Data> dataList = new ArrayList<>();
        for (Project project:projectList) {
            dataList.add(new Data(project.getProject_id(),project.getTitle(),project.getCreated_at(),project.getPlanned_at()));
        }
        return dataList;
    }
}
