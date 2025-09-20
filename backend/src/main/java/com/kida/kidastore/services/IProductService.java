package com.kida.kidastore.services;

import com.kida.kidastore.models.dto.Request.ProductDtoRequest;
import com.kida.kidastore.models.dto.Response.CategoryDtoResponse;
import com.kida.kidastore.models.dto.Response.PagenatedResponse;
import com.kida.kidastore.models.dto.Response.ProductDtoResponse;
import com.kida.kidastore.models.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface IProductService {
    ProductDtoResponse createProduct(ProductDtoRequest productDto);

    ProductDtoResponse readProduct(Long id);

    Product readProductEntity(Long id);

    ProductDtoResponse updateProduct(Long id, ProductDtoRequest productDtoRequest);

    boolean deleteProduct(Long id);


    PagenatedResponse<ProductDtoResponse> getAllProducts(Integer page, Integer size, HttpServletRequest request);

    boolean isProductAvailable(Long id);

    CategoryDtoResponse findCategoryByProductId(Long productId);

    PagenatedResponse<ProductDtoResponse> getProductsByCategoryId(Long categoryId, Integer page, Integer size, HttpServletRequest request);

    long countProducts();
}
