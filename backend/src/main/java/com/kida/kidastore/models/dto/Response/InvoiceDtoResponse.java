package com.kida.kidastore.models.dto.Response;

import com.kida.kidastore.models.entity.Order;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InvoiceDtoResponse {
    private Long id;
    private BigDecimal total;
    private Boolean isPaid;
    private Date creatAt;
}