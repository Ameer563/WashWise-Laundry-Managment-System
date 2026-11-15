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

import com.ameer.laundrymanagment.model.LaundryService;
import com.ameer.laundrymanagment.service.LaundryServiceServices;

import jakarta.validation.Valid;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LaundrySeviceController {
	LaundryServiceServices lss;
	
	public LaundrySeviceController(LaundryServiceServices lss) {
		this.lss=lss;
	}
	
	
	@PostMapping("/service")
	public LaundryService saveService(@Valid @RequestBody LaundryService laundryService) {
		return lss.saveService(laundryService);
	}
	
	@GetMapping("/service")
	public List<LaundryService> getAllService(){
		return lss.getAllServices();
	}
	
	
	@GetMapping("/service/{sid}")
	public LaundryService getServiceBy(@PathVariable Integer sid) {
		return lss.getServiceById(sid);
	}
	
	@PutMapping("/service/{sid}")
	public LaundryService updateService(
			@PathVariable Integer sid,
			@RequestBody LaundryService laundryService) {
		return lss.updateService(sid, laundryService);
	}
	
	@DeleteMapping("/service/{sid}")
	public void deleteService(@PathVariable Integer sid) {
		lss.deleteService(sid);
	}
	

}
