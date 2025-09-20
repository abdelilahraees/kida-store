package com.kida.kidastore.web;

import com.kida.kidastore.models.dto.Request.CustomerDtoRequest;
import com.kida.kidastore.models.dto.Response.CustomerDtoResponse;
import com.kida.kidastore.models.dto.Response.OrderDtoResponse;
import com.kida.kidastore.services.ICustomerService;
import com.kida.kidastore.shared.GlobalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private ICustomerService customerService;


    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }
@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<GlobalResponse<List<CustomerDtoResponse>>> getAllCustomers() {
        List<CustomerDtoResponse> response = customerService.getAllCustomers();
        return ResponseEntity.ok(new GlobalResponse<>(response));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<CustomerDtoResponse>> getCustomerById(@PathVariable String id) {
        CustomerDtoResponse response = customerService.readCustomer(id);
        return ResponseEntity.status(200).body(new GlobalResponse<>(response));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<GlobalResponse<CustomerDtoResponse>> createCustomer(@RequestBody CustomerDtoRequest customerDtoRequest) {
        CustomerDtoResponse response = customerService.createCustomer(customerDtoRequest);
        return ResponseEntity.status(201).body(new GlobalResponse<>(response));
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public ResponseEntity<GlobalResponse<CustomerDtoResponse>>
    updateCustomer(@RequestParam String id, @RequestBody CustomerDtoRequest customerDtoRequest) {
        CustomerDtoResponse response = customerService.updateCustomer(id,
                customerDtoRequest);
        return ResponseEntity.status(201).body(new GlobalResponse<>(response));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<Void>> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();

    }


    @GetMapping("/{customerId}/orders")
    public ResponseEntity<GlobalResponse<List<OrderDtoResponse>>> getOrdersByCustomerId(@PathVariable String customerId) {
        List<OrderDtoResponse> responses = customerService.getOrdersByCustomerId(customerId);
        return ResponseEntity.status(200).body(new GlobalResponse<>(responses));
    }


}
