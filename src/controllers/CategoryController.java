package controllers;

import controllers.interfaces.ICategoryController;
import models.Category;
import repositories.interfaces.ICategoryRepository;

import java.util.List;

public class CategoryController implements ICategoryController {
    private final ICategoryRepository categoryRepository;

    public CategoryController(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String createCategory(String name, String description) {
        Category category = new Category(name, description);
        boolean created = categoryRepository.createCategory(category);
        return created ? "Category created successfully" : "Category creation failed";
    }

    @Override
    public String getCategoryById(int id) {
        Category category = categoryRepository.getCategoryById(id);
        return (category == null) ? "Category not found" : category.toString();
    }

    @Override
    public String getAllCategories() {
        List<Category> categories = categoryRepository.getAllCategories();
        StringBuilder sb = new StringBuilder();
        for (Category category : categories) {
            sb.append(category.toString()).append("\n");
        }
        return sb.toString();
    }
}
