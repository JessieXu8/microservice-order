package com.oocl.microservice.microserviceorder.controller;

import com.oocl.microservice.microserviceorder.pojo.Order;
import com.oocl.microservice.microserviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired OrderService orderService;

    @GetMapping(path = "/order/{orderId}")
    public Order getOrderByOrderId(@PathVariable(name = "orderId") String orderId){
        return orderService.getOrderById(orderId);
    }
}
