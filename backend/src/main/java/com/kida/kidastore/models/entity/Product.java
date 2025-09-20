package com.kida.kidastore.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder @ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Long quantityStock;
    @ManyToOne(fetch =  FetchType.EAGER)
    private Category category;

}
