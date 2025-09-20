package com.kida.kidastore.models.dto.Response;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kida.kidastore.models.dto.Request.CategoryDtoRequest;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDtoResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Long quantityStock;
    private CategoryDtoResponse category;
}
