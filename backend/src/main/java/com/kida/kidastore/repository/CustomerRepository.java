package com.kida.kidastore.repository;

import com.kida.kidastore.models.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
