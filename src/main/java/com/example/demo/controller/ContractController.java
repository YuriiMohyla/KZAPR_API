package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Contract;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    @Autowired
    AttachmentListRepository attachmentListRepository;

    @GetMapping("/contract/{id}")
    public ContractByIdResponseDto getContractById(@PathVariable(value = "id") Long contract_id) {
        Contract contract = contractRepository.getById(contract_id);
        ContractByIdResponseDto.Data.DataProfile customerProfile = new ContractByIdResponseDto.Data.DataProfile(contract.getCustomer().getProfile_id(),
                contract.getCustomer().getName(),contract.getCustomer().getSurname(),
                profileRepository.getUserEmailByProfileId(contract.getCustomer().getProfile_id()));
        ContractByIdResponseDto.Data.DataProfile ownerProfile = new ContractByIdResponseDto.Data.DataProfile(contract.getOwner().getProfile_id(),
                contract.getOwner().getName(),contract.getOwner().getSurname(),
                profileRepository.getUserEmailByProfileId(contract.getOwner().getProfile_id()));
        return new ContractByIdResponseDto("contract data",
                new ContractByIdResponseDto.Data(contract.getContract_id(),contract.getTitle(),contract.getImage(),
                        contract.getDescription(), new ContractByIdResponseDto.Data.DataProfile(customerProfile.getProfileId(),customerProfile.getName(),customerProfile.getSurname(),customerProfile.getEmail()),
                         new ContractByIdResponseDto.Data.DataProfile(ownerProfile.getProfileId(),ownerProfile.getName(),ownerProfile.getSurname(),ownerProfile.getEmail()),
                                ContractByIdResponseDto.Data.attachedDataList(attachmentListRepository.getAttachmentListByContractId(contract.getContract_id())),
                                new ContractByIdResponseDto.Data.Dates(contract.getTime_start(),contract.getTime_end()),
                                new ContractByIdResponseDto.Data.Status(contract.getStatus().getStatus_id(),contract.getStatus().getName(),contract.getStatus().getColor())
                        ),true);
    }

    @GetMapping("/contracts")
    public ContractsListResponseDto getUnclosedContracts() {
        return new ContractsListResponseDto("contracts list",
                ContractsListResponseDto.toDataList(contractRepository.getUnclosedContractList()),true);
    }

    @GetMapping("/project/{profile_id}/project-managers")
    public PmOfProjectDto getAllPmOfProject(@PathVariable(value = "profile_id") Long profile_id) {
        return new PmOfProjectDto("projects for the contract",
                PmOfProjectDto.toDataList(profileRepository.getProfileIdByProjectIdForPm(profile_id)),true);
    }
    @GetMapping("/contract/{contractId}/projects")
    public ListProjectsOfContractDto getUnclosedContracts(@PathVariable(value = "contractId") Long contract_id) {
        return new ListProjectsOfContractDto("projects for the contract",
                ListProjectsOfContractDto.toDataList(projectRepository.getProjectsByContract(contract_id)),true);
    }
    @PostMapping("/contract")
    public ContractResponseDto createNote(@RequestBody ContractRequestDto contractRequestDto) {
        Contract contract = contractRequestDto.toContract();
        if (contractRequestDto.getCustomerId() != null)
            profileRepository.findById(contractRequestDto.getCustomerId()).ifPresent(contract::setCustomer);
        if (contractRequestDto.getCompanyOwnerId() != null)
            profileRepository.findById(contractRequestDto.getCompanyOwnerId()).ifPresent(contract::setOwner);
        if (contractRequestDto.getStatus_id() != null)
            statusRepository.findById(contractRequestDto.getStatus_id()).ifPresent(contract::setStatus);
        contractRepository.save(contract);
        return new ContractResponseDto("contract create", true,
                new ContractResponseDto.ContractDataDTO(contract.getContract_id(),
                        new ContractResponseDto.ContractDataDTO.Stat(contract.getStatus().getStatus_id(),
                                contract.getStatus().getName(), contract.getStatus().getColor())));
    }

}
