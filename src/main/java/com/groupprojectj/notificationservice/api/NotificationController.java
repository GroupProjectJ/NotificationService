package com.groupprojectj.notificationservice.api;

import com.groupprojectj.notificationservice.DTO.NotificationEvent;
import com.groupprojectj.notificationservice.model.NotificationResponse;
import com.groupprojectj.notificationservice.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/health")
    public ResponseEntity<NotificationResponse> health() {
        return ResponseEntity.ok(NotificationResponse.running());
    }

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationEvent event) {
        
    }
}