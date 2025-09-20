package com.kida.kidastore.services.imp;

import com.kida.kidastore.dao.CustomerDao;
import com.kida.kidastore.dao.OrderDao;
import com.kida.kidastore.exception.ResourceNotFoundException;
import com.kida.kidastore.models.dto.Request.CustomerDtoRequest;
import com.kida.kidastore.models.dto.Response.CustomerDtoResponse;
import com.kida.kidastore.models.dto.Response.OrderDtoResponse;
import com.kida.kidastore.models.entity.Cart;
import com.kida.kidastore.models.entity.Customer;
import com.kida.kidastore.models.mappers.CustomerMapper;
import com.kida.kidastore.models.mappers.OrderMapper;
import com.kida.kidastore.services.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements ICustomerService {

    private final OrderMapper orderMapper;
    private CustomerDao customerDao;
    private CustomerMapper customerMapper;
    private OrderDao orderDao;


    public CustomerServiceImpl(CustomerDao customerDao, CustomerMapper customerMapper, OrderDao orderDao, OrderMapper orderMapper) {
        this.customerDao = customerDao;
        this.customerMapper = customerMapper;
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
    }


    @Override
    public CustomerDtoResponse createCustomer(CustomerDtoRequest customerDtoRequest) {
        Customer customer = this.customerMapper.toEntity(customerDtoRequest);
        Cart cart = new Cart();
        customer.setCart(cart);
        cart.setCustomer(customer);
        Customer savedCustomer = this.customerDao.createCustomer(customer);
        return this.customerMapper.toDto(savedCustomer);
    }

    @Override
    public CustomerDtoResponse updateCustomer(String customerId, CustomerDtoRequest customerDtoRequest) {
        Customer existingCustomer = this.customerDao.readCustomer(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        existingCustomer.setUsername(customerDtoRequest.getUsername());
        existingCustomer.setEmail(customerDtoRequest.getEmail());
        existingCustomer.setPhoneNumber(customerDtoRequest.getPhoneNumber());
        existingCustomer.setImgUrl(customerDtoRequest.getImgUrl());
        Customer updatedCustomer = this.customerDao.createCustomer(existingCustomer);
        return this.customerMapper.toDto(updatedCustomer);
    }

    @Override
    public CustomerDtoResponse readCustomer(String id) {
        Customer customer = this.customerDao.readCustomer(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return this.customerMapper.toDto(customer);
    }


    @Override
    public boolean deleteCustomer(String id) {
        customerDao.deleteCustomer(id);
        return !this.customerDao.isCustomerAvailable(id);
    }

    @Override
    public List<CustomerDtoResponse> getAllCustomers() {
        return customerDao.getAllCustomers().stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDtoResponse> getOrdersByCustomerId(String customerId) {
        return orderDao.findOrdersByCustomerId(customerId).stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean isCustomerAvailable(String id) {
        return customerDao.isCustomerAvailable(id);
    }

    @Override
    public Long countCustomrs() {
        return customerDao.countCustomrs();
    }


}
