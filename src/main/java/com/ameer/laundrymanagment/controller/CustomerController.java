package com.ameer.laundrymanagment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ameer.laundrymanagment.model.Customer;
import com.ameer.laundrymanagment.service.CustomerService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController {
       CustomerService customerService;
       
       
       public CustomerController(CustomerService customerService) {
    	   this.customerService=customerService;
       }
       
       
       @PostMapping("/customer")
       public Customer saveCustomer(@Valid @RequestBody Customer c) {
    	   return customerService.saveCustomer(c);
       }
       
       @GetMapping("/customer")
       public List<Customer> getAllCustomers(){
    	   return customerService.getAllCustomers();
       }
       
       
       @GetMapping("/customer/{cid}")
       public Customer getCustomerById(@PathVariable Integer cid) {
    	   return customerService.getCustomerById(cid);
       }
       
       @PutMapping("/customer/{cid}")
       public Customer updateCustomer(@PathVariable Integer cid,@RequestBody Customer c) {
    	   return customerService.updateCustomer(cid, c);
       }
       
       @DeleteMapping("/customer/{cid}")
       public void deleteCustomer(@PathVariable Integer cid) {
    	   customerService.deleteCustomer(cid);
       }
}
