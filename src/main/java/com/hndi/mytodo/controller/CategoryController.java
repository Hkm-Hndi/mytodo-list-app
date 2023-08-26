package com.hndi.mytodo.controller;

import com.hndi.mytodo.model.Category;
import com.hndi.mytodo.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todosCategories")
public class CategoryController {
    private final Logger logger= LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        logger.trace("getting all categories");
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable("id") String id) {
        logger.trace("getting category "+ id);
        return categoryService.getCategory(id);
    }

    @PostMapping
    public void addCategory(@RequestBody Category category) {
        logger.trace("adding category "+ category.getName());
        categoryService.addCategory(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable("id") String id, @RequestBody Category category) {
        logger.trace("updating category "+ id);
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") String id) {
        logger.trace("deleting category "+ id);
        categoryService.deleteCategory(id);
    }
}
