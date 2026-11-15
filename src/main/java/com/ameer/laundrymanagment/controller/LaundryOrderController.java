
package com.ameer.laundrymanagment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ameer.laundrymanagment.model.LaundryOrder;
import com.ameer.laundrymanagment.service.LaundryOrderService;

import jakarta.validation.Valid;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/order")
public class LaundryOrderController {

    LaundryOrderService laundryOrderService;

    public LaundryOrderController(
            LaundryOrderService laundryOrderService) {
        this.laundryOrderService = laundryOrderService;
    }

    @PostMapping
    public LaundryOrder saveOrder(@Valid @RequestBody LaundryOrder lo) {
        return laundryOrderService.saveOrder(lo);
    }

    @GetMapping
    public List<LaundryOrder> getAllOrders() {
        return laundryOrderService.getAllOrders();
    }

    @GetMapping("/{oid:\\d+}")
    public LaundryOrder getOrderById(@PathVariable Integer oid) {
        return laundryOrderService.getOrderById(oid);
    }

    @GetMapping("/late")
    public List<LaundryOrder> getLateOrders() {
        return laundryOrderService.getLateOrders();
    }

    @PutMapping("/{oid}")
    public LaundryOrder updateOrder(
            @PathVariable Integer oid,
            @RequestBody LaundryOrder lo) {
        return laundryOrderService.updateOrder(oid, lo);
    }

    @DeleteMapping("/{oid}")
    public String deleteOrder(@PathVariable Integer oid) {
        laundryOrderService.deleteOrder(oid);
        return "Order is Deleted Successfully";
    }

    @GetMapping("/{oid}/total")
    public LaundryOrder calculationTotal(@PathVariable Integer oid) {
        return laundryOrderService.calculationTotal(oid);
    }

    @PutMapping("/{oid}/status")
    public LaundryOrder updateOrderStatus(
            @PathVariable Integer oid,
            @RequestParam String status) {

        if (!java.util.Set.of(
                "Pending", "Processing", "Ready", "Delivered"
        ).contains(status)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Status must be Pending, Processing, Ready, or Delivered");
        }

        return laundryOrderService.updateOrderStatus(oid, status);
    }
}
