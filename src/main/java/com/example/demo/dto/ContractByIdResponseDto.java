package com.example.demo.dto;

import com.example.demo.model.Attachment;
import com.example.demo.model.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ContractByIdResponseDto {

/*    {
        "message": "contract data",
            "data": {
        "id": 1,
                "title": "",
                "avatarLink": "" || null,
                "description": "" || null,
                "customer": {
            "profileId": 1,
                    "name": "",
                    "surname": "",
                    "email": null
        },
        "companyOwner": {
            "profileId": 2,
                    "name": "",
                    "surname": "",
                    "email": null
        },
        "attached": [
        {
            "type": "file" || "document",
                "title": "" || null,
                "link": ""
        },
    ] || [],
        "dates": {
            "start": "",
                    "end": ""
        },
        status: {
            statusId: 1,
                    name: '',
                    color: '' || NULL,
        },
    },
        "success": true
    }*/

    private String message;
    private Data data;
    private boolean success;

    @AllArgsConstructor
    @Getter
    @ToString
    public static class Data {
        private Long id;
        private String title;
        private String avatarLink;
        private String description;
        private DataProfile customer;
        private DataProfile companyOwner;
        private List<AttachedData> attached;
        private Dates dates;
        private Status status;

        public static List<AttachedData> attachedDataList(List<Attachment> attachmentList) {
            List<AttachedData> dataList = new ArrayList<>();
            for (Attachment attachment:attachmentList) {
                dataList.add(new AttachedData(attachment.getType(),attachment.getAttachment_id(),attachment.getLink()));
            }
            return dataList;
        }

        @AllArgsConstructor
        @Getter
        @ToString
        public static class DataProfile {
            private Long profileId;
            private String name;
            private String surname;
            private String email;
        }
        @AllArgsConstructor
        @Getter
        @ToString
        public static class AttachedData {
            private String type;
            private Long title;
            private String link;
        }
        @AllArgsConstructor
        @Getter
        @ToString
        public static class Dates {
            private String start;
            private String end;
        }
        @AllArgsConstructor
        @Getter
        @ToString
        public static class Status {
            private Long statusId;
            private String name;
            private String color;
        }
    }
}
