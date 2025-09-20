package com.kida.kidastore.services.imp;

import com.kida.kidastore.dao.OrderDao;
import com.kida.kidastore.exception.ResourceNotFoundException;
import com.kida.kidastore.models.dto.Response.OrderDtoResponse;
import com.kida.kidastore.models.entity.Order;
import com.kida.kidastore.models.entity.OrderAddress;
import com.kida.kidastore.models.enums.StateOrder;
import com.kida.kidastore.models.mappers.OrderMapper;
import com.kida.kidastore.services.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {
    private OrderDao orderDao;
    private OrderMapper orderMapper;


    public OrderServiceImpl(OrderDao orderDao, OrderMapper orderMapper) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDtoResponse> getAllOrders() {
        return orderDao.findAllOrders().stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDtoResponse getOrderById(Long orderId) {
        Order order = orderDao.findOrderById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        OrderDtoResponse response = orderMapper.toDto(order);

        response.setAddress(order.getAddress());

        return response;
    }

    @Override
    public List<OrderDtoResponse> getOrdersByCustomerId(String customerId) {
        return orderDao.findOrdersByCustomerId(customerId).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDtoResponse updateOrder(Long orderId, StateOrder stateOrder) {
        Order order = orderDao.findOrderById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        order.setState(stateOrder);
        order = orderDao.updateOrder(order);
        return orderMapper.toDto(order);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderDao.deleteOrderById(orderId);
    }
}
