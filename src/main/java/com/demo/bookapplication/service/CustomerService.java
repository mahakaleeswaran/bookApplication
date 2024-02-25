package com.demo.bookapplication.service;

import com.demo.bookapplication.dto.CustomerDto;
import com.demo.bookapplication.dto.OrderDto;
import com.demo.bookapplication.entity.BookEntity;
import com.demo.bookapplication.entity.CustomerEntity;
import com.demo.bookapplication.entity.OrderEntity;
import com.demo.bookapplication.repository.BookRepository;
import com.demo.bookapplication.repository.CustomerRepository;
import com.demo.bookapplication.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;
    public CustomerDto registerCustomer(CustomerDto customerDto) {
        customerRepository.save(CustomerEntity.builder().name(customerDto.getName()).phoneNumber(customerDto.getPhoneNumber()).address(customerDto.getAddress()).build());
        return customerDto;
    }

    public OrderDto putOrder(Integer id, List<Integer> Ids) {
        Double price= 0.0;
        for(Integer ids:Ids){
            BookEntity bookEntity = bookRepository.findById(ids).orElse(new BookEntity());
            price+=bookEntity.getPrice();

        }
        OrderEntity orderEntity = orderRepository.save(OrderEntity.builder().orderDate(LocalDateTime.now()).price(price).orderStatus(OrderEntity.OrderStatus.PENDING).books(Ids.stream().map((bookId)->bookRepository.findById(bookId).orElse(new BookEntity())).toList().stream().toList()).customer_Id(id).build());
        return OrderDto.builder().orderId(orderEntity.getOrder_id()).orderDate(orderEntity.getOrderDate()).booksNames(orderEntity.getBooks().stream().map(BookEntity::getName).toList()).price(price).orderStatus(orderEntity.getOrderStatus().toString()).build();
    }

    public OrderDto getOrderById(Integer id){
        OrderEntity orderEntity=orderRepository.findById(id).orElse(new OrderEntity());
        return OrderDto.builder().orderId(orderEntity.getOrder_id()).orderDate(orderEntity.getOrderDate()).booksNames(orderEntity.getBooks().stream().map(BookEntity::getName).toList()).price(orderEntity.getPrice()).orderStatus(orderEntity.getOrderStatus().toString()).build();

    }

    public OrderDto cancelOrder(Integer id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElse(new OrderEntity());
        orderEntity.setOrderStatus(OrderEntity.OrderStatus.CANCELLED);
        orderRepository.save(orderEntity);
        return getOrderById(id);
    }

    public List<OrderDto> getAllOrders(Integer id) {
        return customerRepository.findById(id).get().getOrders().stream().map((order)->OrderDto.builder().orderId(order.getOrder_id()).orderDate(order.getOrderDate()).booksNames(order.getBooks().stream().map(BookEntity::getName).toList()).price(order.getPrice()).orderStatus(order.getOrderStatus().toString()).build()).toList();
    }
}
