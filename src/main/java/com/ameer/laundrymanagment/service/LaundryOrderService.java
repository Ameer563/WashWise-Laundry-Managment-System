package com.ameer.laundrymanagment.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ameer.laundrymanagment.model.LaundryOrder;
import com.ameer.laundrymanagment.model.OrderItem;
import com.ameer.laundrymanagment.repository.LaundryOrderRepo;
import com.ameer.laundrymanagment.repository.OrderItemRepo;

@Service
public class LaundryOrderService {

    LaundryOrderRepo lor;
    OrderItemRepo oir;

    public LaundryOrderService(LaundryOrderRepo lor, OrderItemRepo oir) {
        this.lor = lor;
        this.oir = oir;
    }

    public LaundryOrder saveOrder(LaundryOrder lo) {
        return lor.save(lo);
    }

    public List<LaundryOrder> getAllOrders() {
        return lor.findAll();
    }

    public LaundryOrder getOrderById(Integer oid) {
        return lor.findById(oid).orElse(null);
    }

    public LaundryOrder updateOrder(Integer oid, LaundryOrder lo) {
        lo.setOid(oid);
        return lor.save(lo);
    }

    public void deleteOrder(Integer oid) {
        lor.deleteById(oid);
    }

    public LaundryOrder calculationTotal(Integer oid) {

        LaundryOrder order = lor.findById(oid).orElse(null);

        if (order == null) {
            return null;
        }

        List<OrderItem> items = oir.findAll();

        Double total = 0.0;

        for (OrderItem item : items) {

            if (item.getLo() != null
                    && item.getLo().getOid().equals(oid)) {

                total = total + item.getPrice();
            }
        }

        order.setTotalAmount(total);

        return lor.save(order);
    }
    
    public LaundryOrder updateOrderStatus(Integer oid, String status) {

        LaundryOrder lo = lor.findById(oid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found with ID: " + oid));

        lo.setStatus(status);

        if ("Delivered".equals(status)) {
            if (lo.getActualDeliveryDate() == null) {
                lo.setActualDeliveryDate(LocalDate.now());
            }
        } else {
            lo.setActualDeliveryDate(null);
        }

        return lor.save(lo);
    }
    

public List<LaundryOrder> getLateOrders() {
    return lor.findByExpectedDeliveryDateBeforeAndStatusNot(
        LocalDate.now(), "Delivered");
}



public long getTotalOrders() {
    return lor.count();
}

public long getPendingOrders() {
    return lor.countByStatus("Pending");
}

public long getProcessingOrders() {
    return lor.countByStatus("Processing");
}

public long getReadyOrders() {
    return lor.countByStatus("Ready");
}

public long getDeliveredOrders() {
    return lor.countByStatus("Delivered");
}

public long getUndeliveredOrders() {
    return lor.countByStatusNot("Delivered");
}



public long getLateOrderCount() {
    return lor.countByExpectedDeliveryDateBeforeAndStatusNot(
            LocalDate.now(), "Delivered");
}

public double getTotalRevenue() {
    Double revenue = lor.getTotalRevenue();
    return revenue == null ? 0.0 : revenue;
}


    

   

    
    
   
    
    
}