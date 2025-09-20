package com.kida.kidastore.models.mappers;

import com.kida.kidastore.models.dto.Request.CustomerDtoRequest;
import com.kida.kidastore.models.dto.Response.CustomerDtoResponse;
import com.kida.kidastore.models.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, CartMapper.class})
public interface CustomerMapper {

    CustomerDtoResponse toDto(Customer customer);

    Customer toEntity(CustomerDtoRequest customerDtoRequest);
}
