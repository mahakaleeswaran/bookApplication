package com.demo.bookapplication.service;

import com.demo.bookapplication.dto.CustomerDto;
import com.demo.bookapplication.dto.OrderDto;
import com.demo.bookapplication.entity.BookEntity;
import com.demo.bookapplication.entity.CustomerEntity;
import com.demo.bookapplication.entity.OrderEntity;
import com.demo.bookapplication.repository.BookRepository;
import com.demo.bookapplication.repository.CustomerRepository;
import com.demo.bookapplication.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    private final BookRepository bookRepository;
    public CustomerDto registerCustomer(CustomerDto customerDto) {
        if (customerDto.getName() == null || customerDto.getPhoneNumber() == null || customerDto.getAddress() == null) {
            throw new IllegalArgumentException("Incomplete customer information");
        }
        customerRepository.save(CustomerEntity.builder().name(customerDto.getName()).phoneNumber(customerDto.getPhoneNumber()).address(customerDto.getAddress()).build());
        return customerDto;
    }

    public OrderDto putOrder(Integer id, List<Integer> Ids) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid customer id: " + id);
        }
        Double price = 0.0;
        for (Integer bookId : Ids) {
            BookEntity bookEntity = bookRepository.findById(bookId)
                    .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));
            price += bookEntity.getPrice();
        }

        OrderEntity orderEntity = orderRepository.save(OrderEntity.builder()
                .orderDate(LocalDateTime.now())
                .price(price)
                .orderStatus(OrderEntity.OrderStatus.PENDING)
                .books(Ids.stream()
                        .map((bookId) -> bookRepository.findById(bookId)
                                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId)))
                        .toList())
                .customer_Id(id)
                .build());

        return OrderDto.builder()
                .orderId(orderEntity.getOrder_id())
                .orderDate(orderEntity.getOrderDate())
                .booksNames(orderEntity.getBooks().stream().map(BookEntity::getName).toList())
                .price(price)
                .orderStatus(orderEntity.getOrderStatus().toString())
                .build();
    }



    public OrderDto getOrderById(Integer id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));

        return OrderDto.builder()
                .orderId(orderEntity.getOrder_id())
                .orderDate(orderEntity.getOrderDate())
                .booksNames(orderEntity.getBooks().stream().map(BookEntity::getName).toList())
                .price(orderEntity.getPrice())
                .orderStatus(orderEntity.getOrderStatus().toString())
                .build();
    }


    public OrderDto cancelOrder(Integer id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));

        orderEntity.setOrderStatus(OrderEntity.OrderStatus.CANCELLED);
        orderRepository.save(orderEntity);
        return getOrderById(id);
    }


    public List<OrderDto> getAllOrders(Integer id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));

        return customerEntity.getOrders().stream()
                .map((order) -> OrderDto.builder()
                        .orderId(order.getOrder_id())
                        .orderDate(order.getOrderDate())
                        .booksNames(order.getBooks().stream().map(BookEntity::getName).toList())
                        .price(order.getPrice())
                        .orderStatus(order.getOrderStatus().toString())
                        .build())
                .toList();
    }

}
