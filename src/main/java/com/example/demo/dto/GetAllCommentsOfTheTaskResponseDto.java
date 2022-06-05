package com.example.demo.dto;

import com.example.demo.model.Comment;
import com.example.demo.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class GetAllCommentsOfTheTaskResponseDto {

    private String message;
    private List<Data> dataList;
    private boolean success;

    @AllArgsConstructor
    @Getter
    @ToString
    public static class Data {

        private Long id;
        private String text;
        private Author author;
        private Timestamp postTime;
        private Timestamp editTime;
        private String type;

        @AllArgsConstructor
        @Getter
        @ToString
        private static class Author {

            private Long profileId;
            private String avatarLink;
            private String name;
            private String surname;

        }

        public static Author getAuthors(Profile profile){
            return new Author(profile.getProfile_id(), profile.getAvatar(), profile.getName(), profile.getSurname());
        }

    }

}
