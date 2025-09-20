package com.kida.kidastore.models.mappers;

import com.kida.kidastore.models.dto.Response.OrderDtoResponse;
import com.kida.kidastore.models.entity.CartItem;
import com.kida.kidastore.models.entity.Order;
import com.kida.kidastore.models.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderItemMapper {
    OrderDtoResponse toDto(Order order);

    @Mapping(target = "id", ignore = true)
    OrderItem cartItemToOrderItem(CartItem cartItem);

    @Mapping(target = "id", ignore = true)
    List<OrderItem> cartItemsToOrderItems(List<CartItem> cartItems);
}
