package com.example.demo.dto;

import com.example.demo.model.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

/*text
plannedAt
recipientList
priority
*/

@AllArgsConstructor
@Getter
@ToString
public class NotificationDto {

    private String text;
    private Timestamp plannedAt;
    private List<Long> recipient;
    private char priority;

    public Notification toNotification(NotificationDto notificationDto){
        Notification notification = new Notification();
        notification.setText(notificationDto.getText());
        notification.setCreated_at(notificationDto.getPlannedAt());
        notification.setPlanned_at(notificationDto.getPlannedAt());
        notification.setPriority(notificationDto.getPriority());
        return notification;
    }
}

/*{
"text" : "sfhasjf",
"plannedAt" : 10000,
"recipientList" [
    {"profileId" : 1
    },
    {"profileId" : 2
    }
],
"priority" : "S"
}*/