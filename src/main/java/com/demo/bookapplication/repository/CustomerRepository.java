package com.demo.bookapplication.repository;
import com.demo.bookapplication.dto.CustomerDto;
import com.demo.bookapplication.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
}
