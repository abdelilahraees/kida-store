package com.kida.kidastore.dao;

import com.kida.kidastore.dao.CategoryDao;
import com.kida.kidastore.models.entity.Category;
import com.kida.kidastore.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryDao {

    private CategoryRepository categoryRepository;

    public CategoryDao(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }


    public Optional<Category> readCategory(long id) {
        return categoryRepository.findById(id);
    }


    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }


    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
