package com.example.demo.controller;

import com.example.demo.model.Attachment;
import com.example.demo.model.AttachmentList;
import com.example.demo.repository.AttachmentListRepository;
import com.example.demo.repository.AttachmentRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class UploadController {
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AttachmentListRepository attachmentListRepository;
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://temp//";


    @DeleteMapping("/task/{taskId}/attachment/{attachmentId}")
    public String deleteAttachment(@PathVariable long taskId, @PathVariable long attachmentId){
        if (taskRepository.findById(taskId).isPresent() && attachmentRepository.findById(attachmentId).isPresent()){
            try {
                Files.delete(Paths.get(attachmentRepository.getById(attachmentId).getLink()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            attachmentRepository.deleteById(attachmentId);
            return "{\n" +
                    "    \"message\": \"Task file attachment deleted\",\n" +
                    "    \"data\": {\n" +
                    "         id: "+ attachmentId +",\n" +
                    "     },\n" +
                    "    \"success\": true,\n" +
                    "}";
        }
        return "{\n" +
                "    \"message\": \"Task or attachment not found\",\n" +
                "    \"success\": false,\n" +
                "}";
    }

    @PostMapping("/task/{taskId}/attachment")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,@PathVariable long taskId) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            if (!taskRepository.findById(taskId).isPresent()) return "{\n" +
                    "    \"message\": \"Task not found\",\n" +
                    "    \"success\": false,\n" +
                    "}";
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            Attachment attachment = new Attachment();
            attachment.setLink(path.toString());
            attachment.setType("file");
            attachmentRepository.save(attachment);
            AttachmentList attachmentList = new AttachmentList();
            attachmentList.setAttachment(attachment);
            attachmentList.setTask(taskRepository.getById(taskId));
            attachmentListRepository.save(attachmentList);
            String successString = "{\n" +
                    "    \"message\": \"Task attachment added\",\n" +
                    "    \"data\": {\n" +
                    "         link: "+ path.toString() +",\n" +
                    "         id: "+ attachment.getAttachment_id() +",\n" +
                    "    },\n" +
                    "    \"success\": true,\n" +
                    "}";
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            return successString;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:uploadStatus";
    }
}