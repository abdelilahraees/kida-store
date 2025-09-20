package com.kida.kidastore.models.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private Double price;
    private MultipartFile image;
    private String imageUrl;
    private Long quantityStock;
    private Long categoryId;
}
