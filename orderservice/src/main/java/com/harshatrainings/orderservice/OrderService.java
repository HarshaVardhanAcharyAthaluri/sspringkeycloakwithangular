package com.harshatrainings.orderservice;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private List<OrderDto> orderList = new ArrayList<>();

    public List<OrderDto> getOrdersByName(@PathVariable String name) {
        Product product1 = new Product(1,"Books",100);
        Product product2 = new Product(2,"Garments",200);

        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerName("harsha");
        orderDto.setProducts(List.of(product1,product2));

        orderList.add(orderDto);
        return orderList.stream().filter(order->order.getCustomerName().equals(name)).collect(Collectors.toList());
    }

    public OrderDto saveOrder(OrderDto order) {
        orderList.add(order);
        return order;
    }


}
