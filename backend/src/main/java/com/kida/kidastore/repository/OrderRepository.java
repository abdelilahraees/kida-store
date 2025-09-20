package com.kida.kidastore.repository;

import com.kida.kidastore.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByCustomerIdOrderByIdDesc(String customerId);

    List<Order> findAllByOrderByIdDesc();
}