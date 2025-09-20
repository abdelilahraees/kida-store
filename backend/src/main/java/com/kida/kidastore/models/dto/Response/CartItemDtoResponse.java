package com.kida.kidastore.models.dto.Response;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CartItemDtoResponse {
    private Long id;
    private ProductDtoResponse product;
    private Long quantity;
    private BigDecimal total;
}
