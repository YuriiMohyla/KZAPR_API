package com.example.demo.controller;

import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Notification;
import com.example.demo.model.NotificationList;
import com.example.demo.repository.NotificationListRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class NotificationController {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationListRepository notificationListRepository;

    @Autowired
    ProfileRepository profileRepository;

    @PostMapping("/notification")
    public ResponseDto createNotification(@RequestBody NotificationDto notificationDto) {
        Notification notification = notificationDto.toNotification(notificationDto);
        List<Long> recipient = notificationDto.getRecipient();
        notificationRepository.save(notification);
        for(Long r : recipient){
            NotificationList notificationList = new NotificationList();
            notificationList.setProfile(profileRepository.findById(r).get());
            notificationList.setNotification(notification);
            notificationListRepository.save(notificationList);
        }
        return new ResponseDto("new comment", true, null);
    }

}
