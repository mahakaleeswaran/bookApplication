package com.demo.bookapplication.controller;

import com.demo.bookapplication.dto.CustomerDto;
import com.demo.bookapplication.dto.OrderDto;
import com.demo.bookapplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/register")
    public CustomerDto registerCustomer(@RequestBody  CustomerDto customerDto){
        return customerService.registerCustomer(customerDto);
    }

    @PostMapping("/{id}/order")
    public OrderDto putOrder(@PathVariable Integer id, @RequestBody List<Integer> bookId){
        return customerService.putOrder(id,bookId);
    }
}
