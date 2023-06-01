package com.hndi.mytodo.service;

import com.hndi.mytodo.model.Category;
import com.hndi.mytodo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(String id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category updateCategory(String id, Category category) {
        Category categoryDB = categoryRepository.findById(id).get();
        if (Objects.nonNull(category.getName()) &&
                !"".equalsIgnoreCase(category.getName()))
            categoryDB.setName(category.getName());

        if (Objects.nonNull(category.getDescription()) &&
                !"".equalsIgnoreCase(category.getDescription()))
            categoryDB.setDescription(category.getDescription());
        return categoryRepository.save(categoryDB);
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
