package com.ameer.laundrymanagment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ameer.laundrymanagment.model.LaundryService;
import com.ameer.laundrymanagment.repository.LaundryServiceRepo;

@Service
public class LaundryServiceServices {

	LaundryServiceRepo laundryServiceRepo;
	
	public LaundryServiceServices(LaundryServiceRepo laundryServiceRepo) {
		this.laundryServiceRepo=laundryServiceRepo;
	}
	
	
	public LaundryService saveService(LaundryService l) {
		return laundryServiceRepo.save(l);
	}
	
	public List<LaundryService>getAllServices(){
		return laundryServiceRepo.findAll();
	}
	
	public LaundryService getServiceById(Integer sid) {
		return laundryServiceRepo.findById(sid).orElse(null);
	}
	
	public LaundryService updateService(Integer sid,LaundryService laundryService) {
		return laundryServiceRepo.save(laundryService);
	}
	
	public void deleteService(Integer sid) {
		laundryServiceRepo.deleteById(sid);
	}
}
