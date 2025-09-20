package com.kida.kidastore.services.imp;

import com.kida.kidastore.dao.CategoryDao;
import com.kida.kidastore.dao.ProductDao;
import com.kida.kidastore.exception.ResourceNotFoundException;
import com.kida.kidastore.models.dto.Request.ProductDtoRequest;
import com.kida.kidastore.models.dto.Response.CategoryDtoResponse;
import com.kida.kidastore.models.dto.Response.PagenatedResponse;
import com.kida.kidastore.models.dto.Response.ProductDtoResponse;
import com.kida.kidastore.models.entity.Category;
import com.kida.kidastore.models.entity.Product;
import com.kida.kidastore.models.mappers.CategoryMapper;
import com.kida.kidastore.services.IFileUploadService;
import com.kida.kidastore.shared.PagenatedMapper;
import com.kida.kidastore.models.mappers.ProductMapper;
import com.kida.kidastore.services.IProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class ProductServiceImpl implements IProductService {
    private final CategoryDao categoryDao;
    private ProductDao productDao;
    private ProductMapper productMapper;
    private CategoryMapper categoryMapper;
    private PagenatedMapper pagenatedMapper;
    private IFileUploadService fileUploadService;

    public ProductServiceImpl(ProductDao productDao, ProductMapper productMapper, CategoryMapper categoryMapper, PagenatedMapper pagenatedMapper, CategoryDao categoryDao, IFileUploadService fileUploadService) {
        this.productDao = productDao;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
        this.pagenatedMapper = pagenatedMapper;
        this.categoryDao = categoryDao;
        this.fileUploadService = fileUploadService;
    }

    @CacheEvict(value = {"productsCache", "productByIdCache", "productsByCategoryCache"}, allEntries = true)
    @Override
    public ProductDtoResponse createProduct(ProductDtoRequest productDtoRequest) {
        String imageUrl = fileUploadService.storeFile(productDtoRequest.getImage());
        Product product = productMapper.toEntity(productDtoRequest);
        product.setImageUrl(imageUrl);
        if (productDtoRequest.getCategoryId() != null) {
            Category category = categoryDao.readCategory(productDtoRequest.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productDtoRequest.getCategoryId()));
            category.addProduct(product);
        }
        Product savedProduct = productDao.createProduct(product);
        return productMapper.toDto(savedProduct);
    }

    @CacheEvict(value = {"productsCache", "productByIdCache", "productsByCategoryCache"}, allEntries = true)
    @Override
    public ProductDtoResponse updateProduct(Long id, ProductDtoRequest productDtoRequest) {
        Product existingProduct = productDao.readProduct(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        if (productDtoRequest.getImage() != null && !productDtoRequest.getImage().isEmpty()) {
            String imageUrl = this.fileUploadService.storeFile(productDtoRequest.getImage());
            existingProduct.setImageUrl(imageUrl);
        }
        existingProduct.setName(productDtoRequest.getName());
        existingProduct.setDescription(productDtoRequest.getDescription());
        existingProduct.setPrice(productDtoRequest.getPrice());
        existingProduct.setQuantityStock(productDtoRequest.getQuantityStock());

        // 3. Gérer la mise à jour de la catégorie de manière sûre
        Category oldCategory = existingProduct.getCategory();
        Long newCategoryId = productDtoRequest.getCategoryId();

        // On récupère l'ID de l'ancienne catégorie (peut être null)
        Long oldCategoryId = (oldCategory != null) ? oldCategory.getId() : null;

        // Si la catégorie a changé
        if (!Objects.equals(oldCategoryId, newCategoryId)) {
            if (oldCategory != null) {
                oldCategory.removeProduct(existingProduct);
            }
            if (newCategoryId != null) {
                Category newCategory = categoryDao.readCategory(newCategoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + newCategoryId));
                newCategory.addProduct(existingProduct);
            }
        }

        Product updatedProduct = productDao.createProduct(existingProduct);
        return productMapper.toDto(updatedProduct);
    }

    @Cacheable(value = "productByIdCache", key = "#id")
    @Override
    public ProductDtoResponse readProduct(Long id) {
        Product product = productDao.readProduct(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }

    @CacheEvict(value = {"productsCache", "productByIdCache", "productsByCategoryCache"}, allEntries = true)
    @Override
    public boolean deleteProduct(Long id) {
        try {
            productDao.deleteProduct(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting product with id: " + id, e);
        }
        return !this.isProductAvailable(id);
    }

    @Cacheable(value = "productsCache", key = "#page + '-' + #size")
    @Override
    public PagenatedResponse<ProductDtoResponse> getAllProducts(Integer page, Integer size, HttpServletRequest request) {
        page -= 1;
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDtoResponse> productsPage = productDao.getAllProducts(pageable).map(productMapper::toDto);
        String baseUrl = request.getRequestURL().toString();
        return pagenatedMapper.mapToPagenatedResponse(productsPage, baseUrl);
    }

    @Override
    public Product readProductEntity(Long id) {
        return productDao.readProduct(id).
                orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Override
    public CategoryDtoResponse findCategoryByProductId(Long productId) {
        Product product = productDao.readProduct(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
        Category category = product.getCategory();
        if (category == null) {
            throw new ResourceNotFoundException("Category not found for product with id: " + productId);
        }
        return categoryMapper.toDto(category);
    }

    @Cacheable(value = "productsByCategoryCache", key = "#categoryId + '-' + #page + '-' + #size")
    @Override
    public PagenatedResponse<ProductDtoResponse> getProductsByCategoryId(Long categoryId, Integer page, Integer size, HttpServletRequest request) {
        page -= 1;
        System.out.println("Page: " + page + ", Size: " + size);
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDtoResponse> productsPage = productDao.findByCategoryId(categoryId, pageable).map(productMapper::toDto);
        String baseUrl = request.getRequestURL().toString();
        return pagenatedMapper.mapToPagenatedResponse(productsPage, baseUrl);
    }

    @Override
    public boolean isProductAvailable(Long id) {
        return productDao.isProductAvailable(id);
    }

    @Override
    public long countProducts() {
        return productDao.countProducts();
    }
}
