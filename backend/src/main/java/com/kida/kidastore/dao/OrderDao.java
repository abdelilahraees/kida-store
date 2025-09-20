package com.kida.kidastore.dao;

import com.kida.kidastore.dao.OrderDao;
import com.kida.kidastore.models.entity.Order;
import com.kida.kidastore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDao {
    private OrderRepository orderRepository;


    public OrderDao(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<Order> findOrdersByCustomerId(String customerId) {
        return orderRepository.findOrdersByCustomerIdOrderByIdDesc(customerId);
    }


    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);

    }

    public List<Order> findAllOrders() {
        return orderRepository.findAllByOrderByIdDesc();
    }


    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }
}
