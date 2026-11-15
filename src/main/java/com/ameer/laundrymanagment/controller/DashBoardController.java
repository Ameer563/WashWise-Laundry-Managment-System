
package com.ameer.laundrymanagment.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ameer.laundrymanagment.service.LaundryOrderService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

    private final LaundryOrderService los;

    public DashBoardController(LaundryOrderService los) {
        this.los = los;
    }


@GetMapping
public Map<String, Object> getDashboard() {
    Map<String, Object> data = new LinkedHashMap<>();

    data.put("totalOrders", los.getTotalOrders());
    data.put("pendingOrders", los.getPendingOrders());
    data.put("processingOrders", los.getProcessingOrders());
    data.put("readyOrders", los.getReadyOrders());
    data.put("deliveredOrders", los.getDeliveredOrders());
    data.put("undeliveredOrders", los.getUndeliveredOrders());
    data.put("lateOrders", los.getLateOrderCount());
    data.put("totalRevenue", los.getTotalRevenue());

    return data;
}

}

