package com.ameer.laundrymanagment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="customer")
public class Customer {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer cid;
	
	@NotBlank(message="Customer name is Required")
	String name;
	@NotBlank(message="Phone number is Required")
	String ph_no;
	
	@Size(max=250,message="Address must not exceed 250 characters")
	String address;
	
	
	public Customer() {
		
	}


	public Integer getCid() {
		return cid;
	}


	public void setCid(Integer cid) {
		this.cid = cid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPh_no() {
		return ph_no;
	}


	public void setPh_no(String ph_no) {
		this.ph_no = ph_no;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
}