package com.kida.kidastore.models.entity;

import com.kida.kidastore.models.enums.StateOrder;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.service.annotation.GetExchange;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal total;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private StateOrder state = StateOrder.Pending;
    @ManyToOne
    private Customer customer;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Invoice invoice;


    @OneToOne(cascade = CascadeType.ALL)
    private OrderAddress address;


    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }


}
