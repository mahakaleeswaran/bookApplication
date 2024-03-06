package com.demo.bookapplication.service;

import com.demo.bookapplication.dto.CustomerDto;
import com.demo.bookapplication.dto.OrderDto;
import com.demo.bookapplication.entity.BookEntity;
import com.demo.bookapplication.entity.CustomerEntity;
import com.demo.bookapplication.entity.OrderEntity;
import com.demo.bookapplication.repository.BookRepository;
import com.demo.bookapplication.repository.CustomerRepository;
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

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private  CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private BookRepository bookRepository;


    private final OrderDto orderDto=OrderDto.builder().orderId(1).orderStatus("PENDING").price(200.0).orderDate(LocalDateTime.now()).booksNames(List.of("Harry potter","The girl on the train")).build();

    private final BookEntity bookEntity = BookEntity.builder().id(1).name("harry potter").author("j k Rowling").genre("fantasy").price(200.0).build();
    private final OrderEntity orderEntity=OrderEntity.builder().order_id(1).customer_Id(1).orderStatus(OrderEntity.OrderStatus.PENDING).price(200.0).orderDate(LocalDateTime.now()).books(List.of(bookEntity)).build();
    private final CustomerDto customerDto=CustomerDto.builder().name("kalees").address("Madurai").orders(List.of(orderDto)).phoneNumber("9345838095").build();

    private final CustomerEntity customerEntity=CustomerEntity.builder().name("kalees").address("Madurai").orders(List.of(orderEntity)).phoneNumber("9345838095").build();


    @Test
    void registerCustomerIncompleteInformationTest() {
        CustomerDto incompleteCustomerDto = CustomerDto.builder().name("John Doe").build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> customerService.registerCustomer(incompleteCustomerDto));
    }

    @Test
    void registerCustomerTest(){
        CustomerDto response = customerService.registerCustomer(customerDto);
        Assertions.assertEquals(response,customerService.registerCustomer(customerDto));
    }

    @Test
    void putOrderBookIdNotFoundTest() {
        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> customerService.putOrder(1, List.of(1)));
    }

    @Test
    void putOrderTest(){
        Mockito.when(bookRepository.findById(any())).thenReturn(Optional.of(bookEntity));
        Mockito.when(orderRepository.save(any())).thenReturn(orderEntity);
        OrderDto response = customerService.putOrder(1,List.of(1));
        Assertions.assertEquals(response,customerService.putOrder(1,List.of(1)));

    }

    @Test
    void getOrderByIdOrderNotFoundTest() {
        Mockito.when(orderRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> customerService.getOrderById(1));
    }


    @Test
    void  getOrderByIdTest(){
        Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(orderEntity));
        OrderDto response = customerService.getOrderById(1);
        Assertions.assertEquals(response,customerService.getOrderById(1));
    }

    @Test
    void cancelOrderOrderNotFoundTest() {
        Mockito.when(orderRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> customerService.cancelOrder(1));
    }


    @Test
    void cancelOrderTest(){
        Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(orderEntity));
        OrderDto response = customerService.cancelOrder(1);
        Assertions.assertEquals(response,customerService.cancelOrder(1));
    }


    @Test
    void getAllOrdersInvalidCustomerIdTest() {
        Mockito.when(customerRepository.findById(-1)).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> customerService.getAllOrders(-1));
    }




    @Test
    void getAllOrdersTest(){
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.of(customerEntity));
        List<OrderDto> response = customerService.getAllOrders(1);
        Assertions.assertEquals(response,customerService.getAllOrders(1));
    }
}
