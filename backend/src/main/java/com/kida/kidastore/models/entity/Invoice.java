package com.kida.kidastore.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal total;
    private Boolean isPaid;
    private Date creatAt;
    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", total=" + total +
                ", isPaid=" + isPaid +
                ", creatAt=" + creatAt +
                '}';
    }
}
