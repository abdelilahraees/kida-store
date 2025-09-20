package com.kida.kidastore.web;

import com.kida.kidastore.models.dto.Response.OrderDtoResponse;
import com.kida.kidastore.models.enums.StateOrder;
import com.kida.kidastore.services.IOrderService;
import com.kida.kidastore.shared.GlobalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
//@PreAuthorize("hasRole('ADMIN')")
public class OrderController {


    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public ResponseEntity<List<OrderDtoResponse>> getAllOrders() {
        List<OrderDtoResponse> responses = orderService.getAllOrders();
        return ResponseEntity.status(200).body(responses);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDtoResponse> getOrderById(@PathVariable Long orderId) {
        OrderDtoResponse response = orderService.getOrderById(orderId);
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDtoResponse> updateOrder(@PathVariable Long orderId, @ModelAttribute StateOrder stateOrder) {
        OrderDtoResponse response = orderService.updateOrder(orderId, stateOrder);
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }

}
