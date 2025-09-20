package com.kida.kidastore.models.dto.Response;

import com.kida.kidastore.models.entity.OrderAddress;
import com.kida.kidastore.models.enums.StateOrder;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class OrderDtoResponse {
    private Long id;
    private BigDecimal total;
    private Date createdAt;
    private StateOrder state;
    private List<OrderItemResponse> orderItems;
    private OrderAddress address;
}
