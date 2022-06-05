package com.example.demo.dto;

import java.sql.Timestamp;

import com.example.demo.model.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ContractRequestDto {
/*
    title (not required)
    description (not required)
    dateStart (not required)
    dateEnd (not required)
    customerId (not required)
    projectManagerId (not required)
    documents (not required)
    logoImage (not required)
    status (not required)
*/

    private String title;

    private String description;

    private Timestamp time_start;

    private Timestamp time_end;

    private String image;

    private Long customerId;

    private Long companyOwnerId;

    private Long status_id;

    public Contract toContract() {
        Contract contract = new Contract();
        contract.setTitle(title);
        contract.setDescription(description);
        contract.setTime_start(time_start);
        contract.setTime_end(time_end);
        contract.setImage(image);
        return contract;
    }

}

/*
* {
    "title" : "f",
    "description" : "f",
    "time_start" : 100000000,
    "time_end" : 1000000000,
    "customerId" : 1,
    "companyOwnerId" : 2,
    "documents" : "a",
    "image" : "a",
    "status_id" : 2
}*/