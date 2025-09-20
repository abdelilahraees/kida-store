package com.kida.kidastore.web;

import com.kida.kidastore.models.dto.Request.ProductDtoRequest;
import com.kida.kidastore.models.dto.Response.CategoryDtoResponse;
import com.kida.kidastore.models.dto.Response.PagenatedResponse;
import com.kida.kidastore.models.dto.Response.ProductDtoResponse;
import com.kida.kidastore.services.IProductService;
import com.kida.kidastore.shared.GlobalResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<PagenatedResponse<ProductDtoResponse>> getProducts(@RequestParam(required = false) Long categoryId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, HttpServletRequest request) {
        PagenatedResponse<ProductDtoResponse> response;
        if (categoryId != 0) {
            response = this.productService.getProductsByCategoryId(categoryId, page, size, request);
        } else {
            response = this.productService.getAllProducts(page, size, request);
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<ProductDtoResponse>> readProduct(@PathVariable long id) {
        ProductDtoResponse productDtoResponse = this.productService.readProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(new GlobalResponse<>(productDtoResponse));
    }
    @PreAuthorize("hasRole('ADMIN')")

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<GlobalResponse<ProductDtoResponse>> createProduct(@ModelAttribute @Valid ProductDtoRequest productDto) {
        ProductDtoResponse productDtoResponse = this.productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new GlobalResponse<>(productDtoResponse));
    }



@PreAuthorize("hasRole('ADMIN')")
    @PutMapping(consumes = {"multipart/form-data"},value = "/{id}")
    public ResponseEntity<GlobalResponse<ProductDtoResponse>> updateProduct(@ModelAttribute @Valid ProductDtoRequest productDtoRequest,@PathVariable Long id) {
        ProductDtoResponse response = this.productService.updateProduct(id, productDtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new GlobalResponse<>(response));
    }
    @PreAuthorize("hasRole('ADMIN')")

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<Void>> deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productId}/category")
    public ResponseEntity<GlobalResponse<CategoryDtoResponse>> getCategoryByProductId(@PathVariable Long productId) {
        CategoryDtoResponse response = this.productService.findCategoryByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK).body(new GlobalResponse<>(response));
    }


}
