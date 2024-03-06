package com.demo.bookapplication.controller;
import com.demo.bookapplication.dto.CustomerDto;
import com.demo.bookapplication.dto.OrderDto;
import com.demo.bookapplication.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;
    private final OrderDto orderDto=OrderDto.builder().orderId(1).orderStatus("PENDING").price(200.0).orderDate(LocalDateTime.now()).booksNames(List.of("Harry potter","The girl on the train")).build();
    private final CustomerDto customerDto=CustomerDto.builder().name("kalees").address("Madurai").orders(List.of(orderDto)).phoneNumber("9345838095").build();

    @Test
    void registerCustomerTest(){
        Mockito.when(customerService.registerCustomer(any())).thenReturn(customerDto);
        Assertions.assertEquals(customerDto,customerController.registerCustomer(customerDto));
    }

    @Test
    void putOrderInvalidCustomerIdTest() {
        Mockito.when(customerService.putOrder(1, List.of(1, 2))).thenThrow(new IllegalArgumentException("Invalid Customer ID"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> customerController.putOrder(1, List.of(1, 2)));
    }


    @Test
    void putOrderTest(){
        Mockito.when(customerService.putOrder(any(),any())).thenReturn(orderDto);
        Assertions.assertEquals(orderDto,customerController.putOrder(1,List.of(1,2)));
    }

    @Test
    void cancelOrderInvalidOrderIdTest() {
        Mockito.when(customerService.cancelOrder(1)).thenThrow(new IllegalArgumentException("Invalid Order ID"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> customerController.cancelOrder(1));
    }


    @Test
    void cancelOrderTest(){
        Mockito.when(customerService.cancelOrder(any())).thenReturn(orderDto);
        Assertions.assertEquals(orderDto,customerController.cancelOrder(1));
    }
    @Test
    void getAllOrdersTest(){
        Mockito.when(customerService.getAllOrders(any())).thenReturn(List.of(orderDto));
        Assertions.assertEquals(List.of(orderDto),customerController.getAllOrders(1));
    }
}
