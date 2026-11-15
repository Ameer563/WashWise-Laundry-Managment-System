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

import com.ameer.laundrymanagment.model.OrderItem;
import com.ameer.laundrymanagment.service.OrderItemService;

import jakarta.validation.Valid;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OrderItemController {
	
	OrderItemService ois;
	
	
	public OrderItemController(OrderItemService ois) {
		this.ois=ois;
	}
	
	@PostMapping("/orderitem")
	public OrderItem saveOrderItem(@Valid @RequestBody OrderItem orderItem) {
		return ois.saveOrderItem(orderItem);
	}
	
	@GetMapping("/orderitem")
	public List<OrderItem> getAllOrderItems(){
		return ois.getAllOrderItems();
	}
	
	@GetMapping("/orderitem/{itemid}")
	public OrderItem getOrderById(@PathVariable Integer itemid) {
		return ois.getOrderItemById(itemid);
	}
	
	
	@PutMapping("/orderitem/{itemid}")
	public OrderItem updateOrderItem(@PathVariable Integer itemid ,
			@RequestBody OrderItem oi) {
		return ois.updateOrderItem(itemid,oi);
	}
	
	
	@DeleteMapping("/orderitem/{itemid}")
	public String deleteOrderItem(@PathVariable Integer itemid) {
		ois.deleteOrderItem(itemid);
		return "Order Item is Deleted Successfully";
	}

}
