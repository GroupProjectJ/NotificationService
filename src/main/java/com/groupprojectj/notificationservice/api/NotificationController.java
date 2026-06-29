package com.groupprojectj.notificationservice.api;

import com.groupprojectj.notificationservice.DTO.NotificationEvent;
import com.groupprojectj.notificationservice.model.NotificationResponse;
import com.groupprojectj.notificationservice.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Check health", description = "Check health")
    public ResponseEntity<NotificationResponse> health() {
        return ResponseEntity.ok(NotificationResponse.running());
    }

    @PostMapping
    @Operation(summary = "Create a notification", description = "Create a notification")
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationEvent event) {
        return ResponseEntity.accepted().body(notificationService.send(event));
    }
}
