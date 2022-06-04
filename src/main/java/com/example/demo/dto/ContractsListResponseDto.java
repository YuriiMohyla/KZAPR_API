package com.example.demo.dto;

import com.example.demo.model.Contract;
import com.example.demo.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class ContractsListResponseDto {
/*    {
        "message": "contracts list",
            "data": [
        {
            "contractId": 1,
                "title": "",
                "status": "",
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
        private Long contractId;
        private String title;
        private String status;
    }

    public static List<Data> toDataList(List<Contract> contractList) {
        List<Data> dataList = new ArrayList<>();
        for (Contract contract:contractList) {
            dataList.add(new Data(contract.getContract_id(),contract.getTitle(),contract.getStatus().getStatus()));
        }
        return dataList;
    }
}
