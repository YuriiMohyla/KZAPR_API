package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ResponseDto {

    private String message;
    private Boolean success;
    private ResponseDto.DataDTO data;

    @AllArgsConstructor
    @Getter
    @ToString
    @NoArgsConstructor
    public static class DataDTO {
        private Long id;
        private ResponseDto.DataDTO.Stat status;

        @AllArgsConstructor
        @Getter
        @ToString
        public static class Stat {
            private Long statusId;
            private String name;
            private String color;
        }
    }

}
