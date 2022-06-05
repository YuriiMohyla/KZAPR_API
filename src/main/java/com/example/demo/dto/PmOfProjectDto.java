package com.example.demo.dto;

import com.example.demo.model.Profile;
import com.example.demo.model.Project;
import com.example.demo.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class PmOfProjectDto {

    private String massage;
    private List<Data> data;
    private Boolean success;

    @AllArgsConstructor
    @Getter
    @ToString
    public static class Data {
        private Long profileId;
        private String surname;
        private String name;
        private String email;
    }

    public static List<Data> toDataList(List<Profile> projectList) {
        List<Data> dataList = new ArrayList<>();

        for (Profile profile:projectList) {
            dataList.add(new Data(profile.getProfile_id(),profile.getSurname(),profile.getName(),profile.getUser().getEmail()));
        }
        return dataList;
    }
}
