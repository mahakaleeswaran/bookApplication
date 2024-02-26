package com.demo.bookapplication.service;

import com.demo.bookapplication.entity.BookEntity;
import com.demo.bookapplication.entity.OrderEntity;
import com.demo.bookapplication.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @InjectMocks
    private AdminService adminService;
    @Mock
    private OrderRepository orderRepository;
    private final  BookEntity bookEntity = BookEntity.builder().id(1).name("harry potter").author("j k Rowling").genre("fantasy").price(200.0).build();
    private final OrderEntity orderEntity=OrderEntity.builder().order_id(1).customer_Id(1).orderStatus(OrderEntity.OrderStatus.PENDING).price(200.0).orderDate(LocalDateTime.now()).books(List.of(bookEntity)).build();

    @Test
    void  updateOrderStatusToProcessingTest(){
        Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(orderEntity));
        String response = adminService.updateOrderStatusToProcessing(1);
        Assertions.assertEquals(response,adminService.updateOrderStatusToProcessing(1));
    }

    @Test
    void  updateOrderStatusToCompleteTest(){
        Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(orderEntity));
        String response = adminService.updateOrderStatusToCompleted(1);
        Assertions.assertEquals(response,adminService.updateOrderStatusToCompleted(1));
    }
}
