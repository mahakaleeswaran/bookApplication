package com.demo.bookapplication.repository;
import com.demo.bookapplication.dto.OrderDto;
import com.demo.bookapplication.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {
}
