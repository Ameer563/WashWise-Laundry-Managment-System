
package com.ameer.laundrymanagment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ameer.laundrymanagment.model.LaundryOrder;

public interface LaundryOrderRepo extends JpaRepository<LaundryOrder, Integer> {

	

List<LaundryOrder> findByExpectedDeliveryDateBeforeAndStatusNot(
        LocalDate date, String status);


long countByStatus(String status);

long countByStatusNot(String status);



long countByExpectedDeliveryDateBeforeAndStatusNot(
        java.time.LocalDate date, String status);

@org.springframework.data.jpa.repository.Query(
    "SELECT COALESCE(SUM(lo.totalAmount), 0) FROM LaundryOrder lo WHERE lo.paymentStatus = 'Paid'"
)
Double getTotalRevenue();


}