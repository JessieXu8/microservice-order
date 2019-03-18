package com.oocl.microservice.microserviceorder.service;

import com.oocl.microservice.microserviceorder.pojo.Item;
import com.oocl.microservice.microserviceorder.pojo.Order;
import com.oocl.microservice.microserviceorder.pojo.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    @Autowired ItemService itemService;
    private static  final Map<String, Order> MAP  =new HashMap<>();

    static {
        Order order = new Order();
        order.setOrderId("123");
        order.setCreateDate(new Date());
        order.setUpdateDate(order.getCreateDate());
        order.setUserId(1L);

        List<OrderDetail> orderDetails = new ArrayList<>();
        Item item = new Item();
        item.setId(2L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));

        order.setOrderDetails(orderDetails);

        MAP.put(order.getOrderId(), order);
    }

    public Order getOrderById(String orderId){
        Order order = MAP.get(orderId);
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            Item item = itemService.getItemById(orderDetail.getItem().getId());
            orderDetail.setItem(item);
        }

        return order;
    }
}
