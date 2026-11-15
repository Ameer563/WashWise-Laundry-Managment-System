package com.ameer.laundrymanagment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="laundry_service")
public class LaundryService {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	Integer sid;
	
	@NotBlank(message="Service name is required")
	String serviceName;
	
	@Positive(message ="Price must be Greater than zero")
	Double price;
	
	public LaundryService() {
		
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	

}
