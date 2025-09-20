package com.kida.kidastore.models.dto.Response;

import com.kida.kidastore.models.entity.CartItem;
import jakarta.websocket.server.ServerEndpoint;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CartDtoResponse {
    private BigDecimal total;
    private List<CartItemDtoResponse> cartItems;
}
