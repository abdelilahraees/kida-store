package com.kida.kidastore.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> productList;

    public void addProduct(Product product) {
        this.productList.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product) {
        this.productList.remove(product);
        product.setCategory(null);
    }
}
