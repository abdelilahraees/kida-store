package com.kida.kidastore.models.mappers;

import com.kida.kidastore.models.dto.Request.AddressDtoRequest;
import com.kida.kidastore.models.dto.Response.AddressDtoResponse;
import com.kida.kidastore.models.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toEntity(AddressDtoRequest addressDto);

    AddressDtoResponse toDto(Address address);
}
