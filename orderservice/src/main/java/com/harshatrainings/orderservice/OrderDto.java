package com.harshatrainings.orderservice;

import java.util.List;
import java.util.UUID;

public class OrderDto {

    private UUID orderId;
    private String customerName;
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getOrderAmount() {
        return getProducts().stream().mapToInt(Product::getPrice).sum();
    }



    public UUID getOrderId() {
        return UUID.randomUUID();
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
