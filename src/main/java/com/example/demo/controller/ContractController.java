package com.example.demo.controller;

import com.example.demo.dto.ContractResponseDto;
import com.example.demo.dto.ContractRequestDto;
import com.example.demo.model.Contract;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ContractController {

    @Autowired
    ContractRepository contractRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/contract/{id}")
    public Contract getContractById(@PathVariable(value = "id") Long contract_id) {
        return contractRepository.findById(contract_id).orElseThrow(() ->
            new RuntimeException("PIZDA")
        );
    }
    @PostMapping("/contract")
    public ContractResponseDto createNote(@RequestBody ContractRequestDto contractRequestDto) {
        Contract contract = contractRequestDto.toContract();
        profileRepository.findById(contractRequestDto.getCustomerId()).ifPresent(contract::setCustomer);
        profileRepository.findById(contractRequestDto.getCompanyOwnerId()).ifPresent(contract::setOwner);
        statusRepository.findById(contractRequestDto.getStatus_id()).ifPresent(contract::setStatus);
        contractRepository.save(contract);
        return new ContractResponseDto("contract create",true,
                new ContractResponseDto.ContractDataDTO(contract.getContract_id(),contract.getStatus().getStatus_id()));
    }

}
