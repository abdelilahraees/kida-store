package com.kida.kidastore.services;

import com.kida.kidastore.models.dto.Request.CustomerDtoRequest;
import com.kida.kidastore.models.dto.Response.CustomerDtoResponse;
import com.kida.kidastore.models.dto.Response.OrderDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ICustomerService {

    CustomerDtoResponse createCustomer(CustomerDtoRequest customerDtoRequest);

    CustomerDtoResponse updateCustomer(String customerId, CustomerDtoRequest customerDtoRequest);

    CustomerDtoResponse readCustomer(String id);

    boolean deleteCustomer(String id);

    List<CustomerDtoResponse> getAllCustomers();

    List<OrderDtoResponse> getOrdersByCustomerId(String customerId);

    boolean isCustomerAvailable(String id);

    Long countCustomrs();




}
