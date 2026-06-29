package com.groupprojectj.notificationservice.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class NotificationEvent {

    @NotBlank(message = "should be a valid order number")
    private Long orderId;

    @NotBlank(message = "should be a valid customer id")
    private Long customerId;

    @NotBlank(message = "should be a valid product")
    private Long productId;

    private String productName;

    @Positive(message = "Quantity  must be greater than 0")
    private Integer quantity;

    @Positive(message = "Total price must be greater than 0")
    private Double totalPrice;

    private String orderDate;

    private String status;

    public NotificationEvent() {}

    public NotificationEvent(Long orderId,
                             Long customerId,
                             Long productId,
                             String productName,
                             Integer quantity,
                             Double totalPrice,
                             String orderDate,
                             String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}