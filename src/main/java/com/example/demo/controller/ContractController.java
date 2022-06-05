package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Contract;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.ProjectRepository;
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
    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/contract/{id}")
    public Contract getContractById(@PathVariable(value = "id") Long contract_id) {
        return contractRepository.findById(contract_id).orElseThrow(() ->
                new RuntimeException("PIZDA")
        );
    }

    @GetMapping("/contracts")
    public ContractsListResponseDto getUnclosedContracts() {
        return new ContractsListResponseDto("contracts list",
                ContractsListResponseDto.toDataList(contractRepository.getUnclosedContractList()),true);
    }

    @GetMapping("/contract/{contractId}/projects")
    public ListProjectsOfContractDto getUnclosedContracts(@PathVariable(value = "contractId") Long contract_id) {
        return new ListProjectsOfContractDto("projects for the contract",
                ListProjectsOfContractDto.toDataList(projectRepository.getProjectsByContract(contract_id)),true);
    }

    @PostMapping("/contract")
    public ResponseDto createNote(@RequestBody ContractRequestDto contractRequestDto) {
        Contract contract = contractRequestDto.toContract();
        if (contractRequestDto.getCustomerId() != null)
            profileRepository.findById(contractRequestDto.getCustomerId()).ifPresent(contract::setCustomer);
        if (contractRequestDto.getCompanyOwnerId() != null)
            profileRepository.findById(contractRequestDto.getCompanyOwnerId()).ifPresent(contract::setOwner);
        if (contractRequestDto.getStatus_id() != null)
            statusRepository.findById(contractRequestDto.getStatus_id()).ifPresent(contract::setStatus);
        contractRepository.save(contract);
        return new ResponseDto("contract create", true,
                new ResponseDto.DataDTO(contract.getContract_id(),
                        new ResponseDto.DataDTO.Stat(contract.getStatus().getStatus_id(),
                                contract.getStatus().getName(), contract.getStatus().getColor())));
    }

}
