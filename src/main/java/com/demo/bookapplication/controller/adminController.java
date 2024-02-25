package com.demo.bookapplication.controller;
import com.demo.bookapplication.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private AdminService adminService;
    @PutMapping("/{id}/process")
    public String updateOrderStatusToProcessing(@PathVariable Integer id){
         return adminService.updateOrderStatusToProcessing(id);
    }

    @PutMapping("/{id}/complete")
    public String updateOrderStatusToComplete(@PathVariable Integer id){
        return adminService.updateOrderStatusToCompleted(id);
    }
}
