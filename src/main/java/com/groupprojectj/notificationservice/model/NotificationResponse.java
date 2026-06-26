package com.groupprojectj.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

// checkout
@Data
@AllArgsConstructor
public class NotificationResponse {

    private String status;
    private String message;

    public static NotificationResponse running() {
        return new NotificationResponse("UP", "NotificationService is running");
    }

    public static NotificationResponse queued() {
        return new NotificationResponse("QUEUED", "Notification request accepted");
    }
}