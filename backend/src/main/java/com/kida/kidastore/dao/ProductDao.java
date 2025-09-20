package com.kida.kidastore.dao;

import com.kida.kidastore.dao.ProductDao;
import com.kida.kidastore.models.entity.Product;
import com.kida.kidastore.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDao {

    private ProductRepository productRepository;

    public ProductDao(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean isProductAvailable(long id) {
        return productRepository.existsById(id);
    }


    public long countProducts() {
        return productRepository.count();

    }


    public Page<Product> findByCategoryId(Long categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable);
    }


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    public Optional<Product> readProduct(long id) {
        return productRepository.findById(id);
    }


    public Product updateProduct(Product product) {
        productRepository.save(product);
        return product;
    }


    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }
}
