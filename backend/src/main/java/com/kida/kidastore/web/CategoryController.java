package com.kida.kidastore.web;

import com.kida.kidastore.models.dto.Request.CategoryDtoRequest;
import com.kida.kidastore.models.dto.Response.CategoryDtoResponse;
import com.kida.kidastore.models.dto.Response.PagenatedResponse;
import com.kida.kidastore.models.dto.Response.ProductDtoResponse;
import com.kida.kidastore.services.ICategoryService;
import com.kida.kidastore.services.IProductService;
import com.kida.kidastore.shared.GlobalResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private ICategoryService categoryService;
    private IProductService productService;

    public CategoryController(ICategoryService categoryService, IProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<CategoryDtoResponse>>> getCategories() {
        List<CategoryDtoResponse> response = categoryService.getAllCategories();
        return ResponseEntity.ok(new GlobalResponse<>(response));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalResponse<CategoryDtoResponse>> createCategory(@RequestBody CategoryDtoRequest categoryDto) {
        CategoryDtoResponse response = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(201).body(new GlobalResponse<>(response));
    }


    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<CategoryDtoResponse>> getCategoryById(@PathVariable Long id) {
        CategoryDtoResponse response = categoryService.readCategory(id);
        return ResponseEntity.status(200).body(new GlobalResponse<>(response));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalResponse<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalResponse<CategoryDtoResponse>> updateCategory(@PathVariable Long id, @RequestBody CategoryDtoRequest categoryDto) {
        CategoryDtoResponse response = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.status(200).body(new GlobalResponse<>(response));
    }


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{categoryId}/products")
    public ResponseEntity<GlobalResponse<PagenatedResponse<ProductDtoResponse>>> getProductsByCategoryId(@PathVariable Long categoryId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, HttpServletRequest request) {
        PagenatedResponse response = categoryService.getProductsByCategoryId(categoryId, page, size, request);
        return ResponseEntity.status(200).body(new GlobalResponse<>(response));
    }

}
