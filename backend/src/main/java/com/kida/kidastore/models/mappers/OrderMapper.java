package com.kida.kidastore.models.mappers;

import com.kida.kidastore.models.dto.Response.OrderDtoResponse;
import com.kida.kidastore.models.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {
    OrderDtoResponse toDto(Order order);
    Order toEntity(OrderDtoResponse orderDtoResponse);
}
