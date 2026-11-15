package com.ameer.laundrymanagment.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "laundry_order")
public class LaundryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer oid;
    
    @NotNull(message="Order date is required")
    LocalDate orderDate;
    
    private LocalDate actualDeliveryDate;
    
    
    public LocalDate getActualDeliveryDate() {
		return actualDeliveryDate;
	}

	public void setActualDeliveryDate(LocalDate actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}

	
    private LocalDate expectedDeliveryDate;
    
    
    
    public LocalDate getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	@Pattern(
    	    regexp = "Pending|Processing|Ready|Delivered",
    	    message = "Status must be Pending, Processing, Ready, or Delivered"
    	)
    @NotBlank(message ="Order status is required")
    String status;
    
    @NotBlank(message="Payment Status is required")
    String paymentStatus;
    Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "cid")
    Customer customer;

    public LaundryOrder() {
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
