package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ContractResponseDto {
    private String message;
    private Boolean success;
    private ContractDataDTO data;

    @AllArgsConstructor
    @Getter
    @ToString
    public static class ContractDataDTO {
        private Long id;
        private Stat status;

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
