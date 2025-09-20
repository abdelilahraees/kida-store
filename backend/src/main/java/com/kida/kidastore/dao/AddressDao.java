package com.kida.kidastore.dao;

import com.kida.kidastore.dao.AddressDao;
import com.kida.kidastore.exception.ResourceNotFoundException;
import com.kida.kidastore.models.entity.Address;
import com.kida.kidastore.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressDao {


    private AddressRepository addressRepository;

    public AddressDao(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }


    public Optional<Address> readAddress(Long id) {
        return addressRepository.findById(id);
    }

    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }


    public void deleteAddress(long id) {
        addressRepository.deleteById(id);
    }

    public List<Address> findAddressByCustomerId(String customerId) {
        return addressRepository.findAddressByCustomerId(customerId);
    }
}
