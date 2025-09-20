package com.kida.kidastore.models.dto.Request;

import lombok.*;

@Getter @Setter @Builder @ToString @AllArgsConstructor @NoArgsConstructor
public class CategoryDtoRequest {
    private String name;
    private String description;
}
