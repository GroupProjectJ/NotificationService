package com.groupprojectj.notificationservice;

import com.groupprojectj.notificationservice.DTO.NotificationEvent;
import com.groupprojectj.notificationservice.model.NotificationResponse;
import com.groupprojectj.notificationservice.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
class NotificationServiceIntegrationTest {

    @Container
    @ServiceConnection
    static RabbitMQContainer rabbitContainer = new RabbitMQContainer("rabbitmq:3-management");

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // THE HAPPY PATH
    @Test
    void testSend_Success_ShouldPublishMessageAndReturnQueuedStatus() {
        NotificationEvent validEvent = new NotificationEvent(
                1001L, 501L, 9001L, "Wireless Headphones", 2, 199.98, "2026-06-27T10:15:00Z", "CREATED"
        );

        NotificationResponse response = notificationService.send(validEvent);

        assertNotNull(response);
        assertEquals("QUEUED", response.getStatus());
        assertEquals("Notification request accepted", response.getMessage());
    }

    // DATA EDGE CASES (NULL FIELDS)
    @Test
    void testSend_WithNullFields_ShouldNotThrowNullPointerException() {
        NotificationEvent partialEvent = new NotificationEvent();
        partialEvent.setOrderId(null);
        partialEvent.setProductName(null);

        // This ensures your logging string concatenation doesn't throw a NullPointerException
        NotificationResponse response = assertDoesNotThrow(() -> notificationService.send(partialEvent));
        assertEquals("QUEUED", response.getStatus());
    }

    // BROKER FAILURE MODES
    @Test
    void testSend_WhenBrokerIsDown_ShouldThrowAmqpException() {
        // Explicitly stop the Docker container to simulate an infrastructure crash
        rabbitContainer.stop();

        NotificationEvent event = new NotificationEvent(
                1002L, 502L, 9002L, "Mechanical Keyboard", 1, 89.99, "2026-06-28T12:00:00Z", "CREATED"
        );

        // Verifies that your service bubbles up the connection failure correctly so the Controller layer can catch it and return a 503 Service Unavailable instead of a 202 Accepted.
        assertThrows(AmqpException.class, () -> notificationService.send(event));

        // Restart container after test to avoid breaking other tests in the execution suite
        rabbitContainer.start();
    }
}