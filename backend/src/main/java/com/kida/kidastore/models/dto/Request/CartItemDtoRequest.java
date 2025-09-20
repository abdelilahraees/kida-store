package com.kida.kidastore.models.dto.Request;

import com.kida.kidastore.models.entity.CartItem;
import com.kida.kidastore.models.entity.Product;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CartItemDtoRequest {
    private Long productId;
    private Long quantity;
}
