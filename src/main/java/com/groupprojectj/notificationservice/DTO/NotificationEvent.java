package com.groupprojectj.notificationservice.DTO;

public class NotificationEvent {

    private Long id;
    private String message;
    private String recipient;
    private String type;

    public NotificationEvent() {}

    public NotificationEvent(Long id, String message, String recipient, String type) {
        this.id = id;
        this.message = message;
        this.recipient = recipient;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}