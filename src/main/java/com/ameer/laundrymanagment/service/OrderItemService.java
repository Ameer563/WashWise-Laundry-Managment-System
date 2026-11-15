package com.ameer.laundrymanagment.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ameer.laundrymanagment.model.LaundryOrder;
import com.ameer.laundrymanagment.model.LaundryService;
import com.ameer.laundrymanagment.model.OrderItem;
import com.ameer.laundrymanagment.repository.LaundryOrderRepo;
import com.ameer.laundrymanagment.repository.LaundryServiceRepo;
import com.ameer.laundrymanagment.repository.OrderItemRepo;

@Service
public class OrderItemService {
	
	OrderItemRepo oir;
	LaundryServiceRepo lsr;
	LaundryOrderRepo lor;
	
	
	public OrderItemService(OrderItemRepo oir,LaundryServiceRepo lsr,
			LaundryOrderRepo lor) {
		this.oir=oir;
		this.lsr=lsr;
		this.lor=lor;
	}
	
	

    public OrderItem saveOrderItem(OrderItem orderItem) {

    Integer oid = orderItem.getLo().getOid();
    Integer sid = orderItem.getLaundryService().getSid();

    LaundryOrder lo = lor.findById(oid)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Order not found with ID: " + oid));

    LaundryService ls = lsr.findById(sid)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Laundry service not found with ID: " + sid));

    orderItem.setLo(lo);
    orderItem.setLaundryService(ls);

    Double totalPrice = ls.getPrice() * orderItem.getQuantity();
    orderItem.setPrice(totalPrice);

    OrderItem saved = oir.save(orderItem);

    updateOrderTotal(saved.getLo().getOid());

    return saved;
    }

	
	
	public List<OrderItem> getAllOrderItems(){
		return oir.findAll();
	}
	
	public OrderItem getOrderItemById(Integer itemid) {
		return oir.findById(itemid)
	            .orElseThrow(() -> new ResponseStatusException(
	                    HttpStatus.NOT_FOUND,
	                    "Order item not found with ID: " + itemid));
	}
	
	
	
	
	

    public OrderItem updateOrderItem(Integer itemid, OrderItem oi) {

    OrderItem existing = oir.findById(itemid)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Order item not found with ID: " + itemid));

    Integer sid = oi.getLaundryService().getSid();

    LaundryService ls = lsr.findById(sid)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Laundry service not found with ID: " + sid));

    existing.setQuantity(oi.getQuantity());
    existing.setLaundryService(ls);
    existing.setPrice(ls.getPrice() * oi.getQuantity());

    OrderItem saved = oir.save(existing);

    updateOrderTotal(saved.getLo().getOid());

    return saved;
    }

	
	
	



    

	
	
	
	public void deleteOrderItem(Integer itemid) {
	    OrderItem oi = oir.findById(itemid)
	            .orElseThrow(() -> new ResponseStatusException(
	                    HttpStatus.NOT_FOUND,
	                    "Order item not found with ID: " + itemid));

	    Integer oid = oi.getLo().getOid();

	    oir.delete(oi);
	    updateOrderTotal(oid);
	}
	
	
	public void updateOrderTotal(Integer oid) {
	    LaundryOrder lo = lor.findById(oid).orElse(null);

	    if (lo == null) {
	        return;
	    }

	    double total = oir.findAll().stream()
	            .filter(oi -> oi.getLo() != null
	                    && oi.getLo().getOid().equals(oid))
	            .mapToDouble(OrderItem::getPrice)
	            .sum();

	    lo.setTotalAmount(total);
	    lor.save(lo);
	}


	

}
