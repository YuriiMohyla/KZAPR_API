package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Contract;
import com.example.demo.model.Task;
import com.example.demo.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@RestController
@AllArgsConstructor
public class ContractController {

    @Autowired
    private final ContractRepository contractRepository;
    @Autowired
    private final ProfileRepository profileRepository;
    @Autowired
    private final StatusRepository statusRepository;
    @Autowired
    private final ProjectRepository projectRepository;
    @Autowired
    private final AttachmentListRepository attachmentListRepository;
    @Autowired
    private final TaskRepository taskRepository;
    //Get information about the contract by ID
    @GetMapping("/contract/{id}")
    public ContractByIdResponseDto getContractById(@PathVariable(value = "id") Long contract_id) {
        Contract contract = contractRepository.getById(contract_id);
        ContractByIdResponseDto.Data.DataProfile customerProfile = new ContractByIdResponseDto.Data.DataProfile(contract.getCustomer().getProfile_id(),
                contract.getCustomer().getName(), contract.getCustomer().getSurname(),
                profileRepository.getUserEmailByProfileId(contract.getCustomer().getProfile_id()));
        ContractByIdResponseDto.Data.DataProfile ownerProfile = new ContractByIdResponseDto.Data.DataProfile(contract.getOwner().getProfile_id(),
                contract.getOwner().getName(), contract.getOwner().getSurname(),
                profileRepository.getUserEmailByProfileId(contract.getOwner().getProfile_id()));
        return new ContractByIdResponseDto("contract data",
                new ContractByIdResponseDto.Data(contract.getContract_id(), contract.getTitle(), contract.getImage(),
                        contract.getDescription(), new ContractByIdResponseDto.Data.DataProfile(customerProfile.getProfileId(), customerProfile.getName(), customerProfile.getSurname(), customerProfile.getEmail()),
                        new ContractByIdResponseDto.Data.DataProfile(ownerProfile.getProfileId(), ownerProfile.getName(), ownerProfile.getSurname(), ownerProfile.getEmail()),
                        ContractByIdResponseDto.Data.attachedDataList(attachmentListRepository.getAttachmentListByContractId(contract.getContract_id())),
                        new ContractByIdResponseDto.Data.Dates(contract.getTime_start(), contract.getTime_end()),
                        new ContractByIdResponseDto.Data.Status(contract.getStatus().getStatus_id(), contract.getStatus().getName(), contract.getStatus().getColor())
                ), true);
    }

    @GetMapping("/contracts")
    public ContractsListResponseDto getUnclosedContracts() {
        return new ContractsListResponseDto("contracts list",
                ContractsListResponseDto.toDataList(contractRepository.getUnclosedContractList()), true);
    }

    @GetMapping("/project/{profile_id}/project-managers")
    public PmOfProjectDto getAllPmOfProject(@PathVariable(value = "profile_id") Long profile_id) {
        return new PmOfProjectDto("projects for the contract",
                PmOfProjectDto.toDataList(profileRepository.getProfileIdByProjectIdForPm(profile_id)), true);
    }

    @GetMapping("/contract/{contractId}/projects")
    public ListProjectsOfContractDto getUnclosedContracts(@PathVariable(value = "contractId") Long contract_id) {
        return new ListProjectsOfContractDto("projects for the contract",
                ListProjectsOfContractDto.toDataList(projectRepository.getProjectsByContract(contract_id)), true);
    }
    //Creating the contract
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
        log.info("Contract was saved: {}",contract);
        return new ResponseDto("contract create", true,
                new ResponseDto.DataDTO(contract.getContract_id(),
                        new ResponseDto.DataDTO.Stat(contract.getStatus().getStatus_id(),
                                contract.getStatus().getName(), contract.getStatus().getColor())));
    }
    //Change the contract information by ID
    @PutMapping("/contract/{contractId}")
    public ResponseDto changeContract(@RequestBody ContractRequestDto contractRequestDto,@PathVariable(value = "contractId") Long contract_id) {
        if (!contractRepository.findById(contract_id).isPresent()) {
            return new ResponseDto("Contract with id" + contract_id + "not found",false, new ResponseDto.DataDTO());
        }
        Contract contract = contractRepository.getById(contract_id);
        contract.setTitle(contractRequestDto.getTitle());
        contract.setImage(contractRequestDto.getImage());
        contract.setTime_start(contractRequestDto.getTime_start());
        contract.setTime_end(contractRequestDto.getTime_end());

        if (contractRequestDto.getCustomerId() != null)
            profileRepository.findById(contractRequestDto.getCustomerId()).ifPresent(contract::setCustomer);
        if (contractRequestDto.getCompanyOwnerId() != null)
            profileRepository.findById(contractRequestDto.getCompanyOwnerId()).ifPresent(contract::setOwner);
        if (contractRequestDto.getStatus_id() != null)
            statusRepository.findById(contractRequestDto.getStatus_id()).ifPresent(contract::setStatus);
        contractRepository.save(contract);
        return new ResponseDto("change contract", true,
                new ResponseDto.DataDTO(contract.getContract_id(),
                        new ResponseDto.DataDTO.Stat(contract.getStatus().getStatus_id(),
                                contract.getStatus().getName(), contract.getStatus().getColor())));
    }
    //Get the completed task per the day of the week
    @GetMapping("/amount-of-the-complited-tasks")
    public TaskPerWeekDto getTasksPerWeek() {
        final LocalDate currentWeek = LocalDate.now().minusDays(7);
        final LocalDate endOfWeek = LocalDate.now();

        List<Task> tasks = taskRepository.getTasksByStatusName("Closed")
                .stream()
                .filter(task -> currentWeek.isBefore(task.getTime_end().toLocalDateTime().toLocalDate()))
                .collect(Collectors.toList());

        List<TaskPerWeekDto.TaskPerWeek> taskPerWeek = new ArrayList<>();

        for (LocalDate iterator = currentWeek; iterator.isBefore(endOfWeek); iterator = iterator.plusDays(1)) {
            DayOfWeek dayOfWeek = iterator.getDayOfWeek();
            long count = tasks.stream()
                    .filter(task -> task.getTime_end().toLocalDateTime().getDayOfWeek().equals(dayOfWeek))
                    .count();

            taskPerWeek.add(new TaskPerWeekDto.TaskPerWeek(dayOfWeek.name(), (int) count));
        }
        return new TaskPerWeekDto(taskPerWeek);
    }
}
