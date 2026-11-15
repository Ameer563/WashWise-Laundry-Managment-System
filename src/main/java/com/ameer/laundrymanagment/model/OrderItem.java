package com.ameer.laundrymanagment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer itemid;
    
    @Positive(message="Qunatity must be greater than 0")
    Integer quantity;
    Double price;

    @ManyToOne
    @JoinColumn(name = "oid")
    LaundryOrder lo;

    @ManyToOne
    @JoinColumn(name = "sid")
    LaundryService laundryService;

    public OrderItem() {
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LaundryOrder getLo() {
        return lo;
    }

    public void setLo(LaundryOrder lo) {
        this.lo = lo;
    }

    public LaundryService getLaundryService() {
        return laundryService;
    }

    public void setLaundryService(LaundryService laundryService) {
        this.laundryService = laundryService;
    }
}