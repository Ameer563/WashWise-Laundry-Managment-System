
package com.ameer.laundrymanagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ameer.laundrymanagment.model.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {

}