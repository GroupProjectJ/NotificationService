package com.groupprojectj.notificationservice.service;

import com.groupprojectj.notificationservice.DTO.NotificationEvent;
import com.groupprojectj.notificationservice.model.NotificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public NotificationService(RabbitTemplate rabbitTemplate,
            @Value("${notification.rabbit.exchange:notification.direct.exchange}") String exchangeName,
            @Value("${notification.rabbit.routing-key:notification.created}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public NotificationResponse send(NotificationEvent event) {
        log.info("Publishing order event {} for product {}", event.getOrderId(), event.getProductName());
        rabbitTemplate.convertAndSend(exchangeName, routingKey, event);
        return NotificationResponse.queued();
    }
}