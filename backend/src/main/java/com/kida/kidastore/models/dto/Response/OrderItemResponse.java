package com.kida.kidastore.models.dto.Response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class OrderItemResponse {
    private Long id;
    private ProductDtoResponse product;
    private Long quantity;
}
