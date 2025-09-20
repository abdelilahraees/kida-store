package com.kida.kidastore.dao;

import com.kida.kidastore.dao.CustomerDao;
import com.kida.kidastore.models.entity.Customer;
import com.kida.kidastore.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDao{
    private CustomerRepository customerRepository;


    public CustomerDao(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer createCustomer(Customer Customer) {
        return customerRepository.save(Customer);
    }


    public Optional<Customer> readCustomer(String id) {
        return customerRepository.findById(id);
    }


    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }


    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public boolean isCustomerAvailable(String id) {
        return customerRepository.existsById(id);
    }

    public long countCustomrs() {
        return customerRepository.count();
    }
}
