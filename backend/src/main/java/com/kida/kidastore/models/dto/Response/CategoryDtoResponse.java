package com.kida.kidastore.models.dto.Response;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kida.kidastore.models.entity.Product;
import lombok.*;

import java.util.List;

@Getter @Setter @Builder @ToString @AllArgsConstructor @NoArgsConstructor
public class CategoryDtoResponse {
    private Long id;
    private String name;
    private String description;

}
