package com.hndi.mytodo.service;

import com.hndi.mytodo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CategoryService {

    public void addCategory(Category category);
    public List<Category> getAllCategories();
    public Category getCategory(String id);
    public Category updateCategory(String id, Category category);
    public void deleteCategory(String id);
}
