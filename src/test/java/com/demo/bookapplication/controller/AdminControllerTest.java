package com.demo.bookapplication.controller;

import com.demo.bookapplication.service.AdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;
    @Mock
    private AdminService adminService;

    @Test
    void updateOrderStatusToProcessingTest(){
        Mockito.when(adminService.updateOrderStatusToProcessing(any())).thenReturn("Sucess");
        Assertions.assertEquals("Sucess",adminController.updateOrderStatusToProcessing(1));
    }

    @Test
    void updateOrderStatusToCompleteTest(){
        Mockito.when(adminService.updateOrderStatusToCompleted(any())).thenReturn("Sucess");
        Assertions.assertEquals("Sucess",adminController.updateOrderStatusToComplete(1));
    }
}
