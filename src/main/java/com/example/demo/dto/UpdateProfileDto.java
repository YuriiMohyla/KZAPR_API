package com.example.demo.dto;

import com.example.demo.model.Comment;
import com.example.demo.model.Profile;
import com.example.demo.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.stream.Collectors;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class UpdateProfileDto {

    private String email;
    private String name;
    private String surname;
    private Date birthdayDate;
    private String aboutMe;

}

/*{
        "email" : "sdgfhasjkvghb",
        "name" : "fsdhgsjk",
        "surname" : "fklashfjksd",
        "birthdayDate" : 100000,
        "aboutMe" : "sdjkhfjksdghbsjkl"
        }*/
