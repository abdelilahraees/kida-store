package com.kida.kidastore.services;

import com.kida.kidastore.models.dto.Response.OrderDtoResponse;
import com.kida.kidastore.models.enums.StateOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService {
    List<OrderDtoResponse> getAllOrders();

    OrderDtoResponse getOrderById(Long orderId);

    List<OrderDtoResponse> getOrdersByCustomerId(String customerId);

    OrderDtoResponse updateOrder(Long orderId, StateOrder stateOrder);

    void deleteOrderById(Long orderId);
}
