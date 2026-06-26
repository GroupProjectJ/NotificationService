package com.groupprojectj.notificationservice.listener;

import com.groupprojectj.notificationservice.config.RabbitMqConfig;
import com.groupprojectj.notificationservice.model.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationListener {

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void receiveNotification(NotificationRequest notificationRequest) {
        log.info("Received notification for {} with subject {}", notificationRequest.getRecipient(), notificationRequest.getSubject());
    }
}