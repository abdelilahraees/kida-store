package com.kida.kidastore.web;

import com.kida.kidastore.models.dto.Request.OrderAddressDtoReq;
import com.kida.kidastore.models.dto.Response.InvoiceDtoResponse;
import com.kida.kidastore.models.dto.Response.OrderDtoResponse;
import com.kida.kidastore.services.IProfilService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfilOrderController {


    private IProfilService profilService;

    public ProfilOrderController(IProfilService profilService) {
        this.profilService = profilService;
    }


    @GetMapping
    public String getCurrentCustomer() {
        return profilService.getCurrentCustomer().getEmail();
    }


    @PostMapping("/orders")
    public ResponseEntity<OrderDtoResponse> addOrder(@ModelAttribute @Valid OrderAddressDtoReq addressDtoReq) {
        OrderDtoResponse response = profilService.addOrder(addressDtoReq);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDtoResponse>> getAllOrders() {
        List<OrderDtoResponse> responses = profilService.getAllOrders();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDtoResponse> getOrderById(@PathVariable Long id) {
        OrderDtoResponse response = profilService.getOrderById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/orders/{orderId}/invoice")
    public ResponseEntity<InvoiceDtoResponse> getInvoiceByOrderId(@PathVariable Long orderId) {
        InvoiceDtoResponse response = profilService.getInvoiceByOrderId(orderId);
        return ResponseEntity.ok().body(response);
    }
}
