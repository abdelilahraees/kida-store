package com.kida.kidastore.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantity = 1L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private BigDecimal total;

    @Transient // Indique à JPA de ne pas créer de colonne pour cette méthode
    public BigDecimal getTotal() {
        if (product != null && product.getPrice() != null) {
            BigDecimal total = BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(quantity));
            return total;
        }
        return BigDecimal.ZERO;
    }

}
