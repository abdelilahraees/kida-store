package com.kida.kidastore.models.mappers;

import com.kida.kidastore.models.dto.Response.CartDtoResponse;
import com.kida.kidastore.models.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface CartMapper {
    CartDtoResponse toDto(Cart cart);
}
