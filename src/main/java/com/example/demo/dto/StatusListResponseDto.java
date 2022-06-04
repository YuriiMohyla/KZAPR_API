package com.example.demo.dto;

import com.example.demo.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class StatusListResponseDto {


    /*    {
            "message": "get status",
                "data": [
            {
                statusId: 1,
                        name: '',
                    color: '',
                    status: '',
        ...
      ],
                "success": true,
            }*/
    private String massage;
    private List<Data> data;
    private Boolean success;

    @AllArgsConstructor
    @Getter
    @ToString
    public static class Data {
        private Long statusId;
        private String name;
        private String color;
        private String status;
    }

    public static List<Data> toDataList(List<Status> statusList) {
        List<Data> dataList = new ArrayList<>();

        for (Status status:statusList) {
            dataList.add(new Data(status.getStatus_id(),status.getName(),status.getColor(),status.getStatus()));
        }
        return dataList;
    }
}
