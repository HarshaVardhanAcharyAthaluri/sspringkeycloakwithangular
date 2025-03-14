package com.harshatrainings.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders/{name}")
    public List<OrderDto> getOrders(@PathVariable String name) {
        return orderService.getOrdersByName(name);
    }

    @PostMapping("/saveorder")
    public OrderDto saveOrder(@RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }

}
