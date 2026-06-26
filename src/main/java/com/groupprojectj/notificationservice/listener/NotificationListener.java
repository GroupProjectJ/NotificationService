package com.groupprojectj.notificationservice.listener;

import com.groupprojectj.notificationservice.DTO.NotificationEvent;
import com.groupprojectj.notificationservice.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationListener {

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public void receiveNotification(NotificationEvent notificationEvent) {
        log.info("Received order event: orderId={}, customerId={}, productId={}, productName={}, quantity={}, totalPrice={}, orderDate={}, status={}",
                notificationEvent.getOrderId(),
                notificationEvent.getCustomerId(),
                notificationEvent.getProductId(),
                notificationEvent.getProductName(),
                notificationEvent.getQuantity(),
                notificationEvent.getTotalPrice(),
                notificationEvent.getOrderDate(),
                notificationEvent.getStatus());
    }

    @RabbitListener(queues = RabbitMqConfig.DEAD_LETTER_QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public void receiveDeadLetter(String rawMessage) {
        log.error("Malformed or rejected message moved to DLQ: {}", rawMessage);
    }
}