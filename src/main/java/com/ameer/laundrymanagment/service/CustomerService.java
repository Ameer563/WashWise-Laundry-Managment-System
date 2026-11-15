package com.ameer.laundrymanagment.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ameer.laundrymanagment.model.Customer;
import com.ameer.laundrymanagment.repository.CustomerRepo;

@Service
public class CustomerService {
   
	
	CustomerRepo customerRepo;
	
	public CustomerService(CustomerRepo customerRepo) {
		this.customerRepo=customerRepo;
	}
	
	public Customer saveCustomer(Customer c) {
		return customerRepo.save(c);
	}
	
	
	public List<Customer> getAllCustomers(){
		return customerRepo.findAll();
	}
	
	
	public Customer getCustomerById(Integer cid) {
		return customerRepo.findById(cid).orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Customer not found with ID:"+cid));
    }
	
	public Customer updateCustomer(Integer cid,Customer c) {
		c.setCid(cid);
		return customerRepo.save(c);
	}
	
	public void deleteCustomer(Integer cid) {
		customerRepo.deleteById(cid);
	}
}
