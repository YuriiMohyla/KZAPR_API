package com.example.demo.dto;

import com.example.demo.model.Profile;
import com.example.demo.model.Roles;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UserResponseDto {
    private String message;
    private List<UserData> data;
    private boolean success;


    @Getter
    @Setter
    @ToString
    public static class UserData {
        private long profileId;
        private String name;
        private String surname;
        private String email;
        private List<String> position;
        private Date birthday;
    }
    public static List<UserData> fromProfileList(List<Profile> profiles) {
        List<UserData> userDataList = new ArrayList<>();
        UserData userData = new UserData();
        for (Profile profile:profiles) {
            userData.setProfileId(profile.getProfile_id());
            userData.setName(profile.getName());
            userData.setSurname(profile.getSurname());
            userData.setEmail(profile.getUser().getEmail());
            userData.setBirthday(profile.getBirthday());
            userData.setPosition(profile.getRoles().stream().map(Roles::getName).collect(Collectors.toList()));
            userDataList.add(userData);
        }
        return userDataList;
    }
}

