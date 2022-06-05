package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    // Получить все записи
    @GetMapping("/task")
    public List getAllNotes() {
        return taskRepository.findAll();
    }

    @PostMapping("/task")
    public ResponseDto createNote(@RequestBody TaskRequestDto taskRequestDto) {
        Task task = taskRequestDto.toTask();
        if (taskRequestDto.getProjectId() != null)
            projectRepository.findById(taskRequestDto.getProjectId()).ifPresent(task::setProject);
        if (taskRequestDto.getParentId() != null)
            taskRepository.findById(taskRequestDto.getParentId()).ifPresent(task::setTask);
        if (taskRequestDto.getStatusId() != null)
            statusRepository.findById(taskRequestDto.getStatusId()).ifPresent(task::setStatus);
        taskRepository.save(task);
        return new ResponseDto("task create", true,
                new ResponseDto.DataDTO(task.getTask_id(),
                        new ResponseDto.DataDTO.Stat(task.getStatus().getStatus_id(),
                                task.getStatus().getName(), task.getStatus().getColor())));
    }

    @GetMapping("/task/{taskId}/comments")
    public GetAllCommentsOfTheTaskResponseDto getAllComentsOfTheTask(@PathVariable(value = "taskId") Long taskId) {

        List<Comment> comments = commentRepository.getAllCommentsOfTheTaskList(taskId);
        List<Profile> profiles = profileRepository.getAllAuthorsOfTheTaskList(taskId);
        List<GetAllCommentsOfTheTaskResponseDto.Data> data = new ArrayList<>();

        for(int i = 0; i < comments.size(); i++) {
            data.add(new GetAllCommentsOfTheTaskResponseDto.Data(
                    comments.get(i).getComment_id(),
                    comments.get(i).getText(),
                    GetAllCommentsOfTheTaskResponseDto.Data.getAuthors(profiles.get(i)),
                    comments.get(i).getCreated_at(),
                    comments.get(i).getEdited_at(),
                    comments.get(i).getType()));
        }

        GetAllCommentsOfTheTaskResponseDto getAllCommentsOfTheTaskResponseDto = new GetAllCommentsOfTheTaskResponseDto(
                "get post comments", data,true);

        return getAllCommentsOfTheTaskResponseDto;
    }

    @GetMapping("/task/{taskId}")
    public GetInformationAboutTaskByIdResponseDto getInformationAboutTaskByIdResponseDto(@PathVariable(value = "taskId") Long taskId) {

        Task task = taskRepository.getTaskByTaskId(taskId);
        Status status = statusRepository.getStatusByTaskId(taskId);
        Project project = projectRepository.getProjectsByTaskId(taskId);
        Profile profile = profileRepository.getProfileByTaskId(taskId);
        User user = userRepository.getUserByTaskId(taskId);
        List<Attachment> attachments = attachmentRepository.getUserByTaskId(taskId);

        List<GetInformationAboutTaskByIdResponseDto.Data.Attached> attached = new ArrayList<>();

        for(Attachment a : attachments){
            attached.add(GetInformationAboutTaskByIdResponseDto.Data.toAttached(a));
        }

        GetInformationAboutTaskByIdResponseDto.Data data = new GetInformationAboutTaskByIdResponseDto.Data(
                task.getTask_id(),
                task.getTitle(),
                task.getDescription(),
                GetInformationAboutTaskByIdResponseDto.Data.toStatus(status),
                GetInformationAboutTaskByIdResponseDto.Data.toProject(project),
                GetInformationAboutTaskByIdResponseDto.Data.toAssignTo(profile, user),
                task.getTime_start(),
                task.getTime_end(),
                task.getColor(),
                attached
        );

        GetInformationAboutTaskByIdResponseDto getInformationAboutTaskByIdResponseDto = new GetInformationAboutTaskByIdResponseDto("post data", data, true);

        return getInformationAboutTaskByIdResponseDto;

    }

}