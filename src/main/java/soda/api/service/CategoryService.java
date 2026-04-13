package soda.api.service;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import soda.api.entity.Category;
import soda.api.repository.CategoryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> get() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getById(int id) {
        return categoryRepository.findById(id);
    }

    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    public Category update(int id, Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setDescription(category.getDescription());
            return categoryRepository.save(updatedCategory);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada");
        }
    }
}