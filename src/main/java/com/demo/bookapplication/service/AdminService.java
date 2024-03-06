package com.demo.bookapplication.service;

import com.demo.bookapplication.entity.OrderEntity;
import com.demo.bookapplication.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminService {


    @Autowired
    private OrderRepository orderRepository;
    public String updateOrderStatusToProcessing(Integer id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
        orderEntity.setOrderStatus(OrderEntity.OrderStatus.PROCESSING);
        orderRepository.save(orderEntity);
        return "Status Updated";
    }

    public String updateOrderStatusToCompleted(Integer id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
        orderEntity.setOrderStatus(OrderEntity.OrderStatus.COMPLETED);
        orderRepository.save(orderEntity);
        return "Status Updated";
    }

}
