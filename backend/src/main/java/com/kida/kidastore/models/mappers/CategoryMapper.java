package com.kida.kidastore.models.mappers;

import com.kida.kidastore.models.dto.Request.CategoryDtoRequest;
import com.kida.kidastore.models.dto.Response.CategoryDtoResponse;
import com.kida.kidastore.models.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {ProductMapper.class})
public interface CategoryMapper {
    CategoryDtoResponse toDto(Category category);
    Category toEntity(CategoryDtoRequest categoryDto);
}
