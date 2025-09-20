package com.kida.kidastore.models.mappers;

import com.kida.kidastore.models.dto.Request.ProductDtoRequest;
import com.kida.kidastore.models.dto.Response.ProductDtoResponse;
import com.kida.kidastore.models.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDtoRequest productDto);

    ProductDtoResponse toDto(Product product);
}
