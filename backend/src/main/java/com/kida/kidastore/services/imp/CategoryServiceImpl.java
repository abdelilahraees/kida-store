package com.kida.kidastore.services.imp;

import com.kida.kidastore.dao.CategoryDao;
import com.kida.kidastore.exception.ResourceNotFoundException;
import com.kida.kidastore.models.dto.Request.CategoryDtoRequest;
import com.kida.kidastore.models.dto.Response.CategoryDtoResponse;
import com.kida.kidastore.models.dto.Response.PagenatedResponse;
import com.kida.kidastore.models.dto.Response.ProductDtoResponse;
import com.kida.kidastore.models.entity.Category;
import com.kida.kidastore.models.mappers.CategoryMapper;
import com.kida.kidastore.services.ICategoryService;
import com.kida.kidastore.services.IProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private CategoryDao categoryDao;
    private CategoryMapper categoryMapper;
    private IProductService productService;

    public CategoryServiceImpl(CategoryDao categoryDao, CategoryMapper categoryMapper, @Qualifier("productServiceImpl") IProductService productService) {
        this.categoryDao = categoryDao;
        this.categoryMapper = categoryMapper;
        this.productService = productService;
    }

    @Override
    public CategoryDtoResponse createCategory(CategoryDtoRequest categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category savedCategory = categoryDao.createCategory(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDtoResponse readCategory(Long id) {
        Category category = categoryDao.readCategory(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public Category readCategoryEntity(Long id) {
        return categoryDao.readCategory(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    public CategoryDtoResponse updateCategory(Long id, CategoryDtoRequest categoryDto) {
        Category existingCategory = categoryDao.readCategory(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        existingCategory.setName(categoryDto.getName());
        existingCategory.setDescription(categoryDto.getDescription());
        Category updatedCategory = categoryDao.updateCategory(existingCategory);
        return categoryMapper.toDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryDao.deleteCategory(id);
    }

    @Override
    public List<CategoryDtoResponse> getAllCategories() {
        return categoryDao.getAllCategories().stream()
                .map(category -> categoryMapper.toDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public PagenatedResponse<ProductDtoResponse> getProductsByCategoryId(Long categoryId, Integer page, Integer size, HttpServletRequest request) {
        return productService.getProductsByCategoryId(categoryId, page, size, request);
    }


}
