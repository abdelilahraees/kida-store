package com.kida.kidastore.models.mappers;

import com.kida.kidastore.models.dto.Request.CartItemDtoRequest;
import com.kida.kidastore.models.dto.Response.CartItemDtoResponse;
import com.kida.kidastore.models.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface ItemMapper {
    CartItem toEntity(CartItemDtoRequest cartItemDto);
    CartItemDtoResponse toDto(CartItem cartItem);
}
