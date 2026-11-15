package com.ameer.laundrymanagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ameer.laundrymanagment.model.LaundryService;

public interface LaundryServiceRepo extends JpaRepository<LaundryService, Integer>{

}
