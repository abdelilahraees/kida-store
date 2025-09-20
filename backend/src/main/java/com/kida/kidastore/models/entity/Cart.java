package com.kida.kidastore.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.LifecycleState;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Builder
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JoinColumn(name = "cart_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<CartItem> cartItems;

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public void removeCartItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
    }

    public void clearCart() {
        this.cartItems.clear();
    }

    public BigDecimal getTotal() {
        BigDecimal total = cartItems.stream().map(CartItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }
}
