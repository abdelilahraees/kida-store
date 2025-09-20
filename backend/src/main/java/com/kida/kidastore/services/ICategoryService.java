package com.kida.kidastore.services;

import com.kida.kidastore.models.dto.Request.CategoryDtoRequest;
import com.kida.kidastore.models.dto.Response.CategoryDtoResponse;
import com.kida.kidastore.models.dto.Response.PagenatedResponse;
import com.kida.kidastore.models.dto.Response.ProductDtoResponse;
import com.kida.kidastore.models.entity.Category;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {
    CategoryDtoResponse createCategory(CategoryDtoRequest categoryDtoRequest);

    CategoryDtoResponse readCategory(Long id);

    Category readCategoryEntity(Long id);

    CategoryDtoResponse updateCategory(Long id, CategoryDtoRequest categoryDtoRequest);

    void deleteCategory(Long id);

    List<CategoryDtoResponse> getAllCategories();

    PagenatedResponse<ProductDtoResponse> getProductsByCategoryId(Long categoryId, Integer page, Integer size, HttpServletRequest request);
}
