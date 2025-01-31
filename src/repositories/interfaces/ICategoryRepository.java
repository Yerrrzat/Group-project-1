package repositories.interfaces;

import models.Category;
import java.util.List;

public interface ICategoryRepository {
    boolean createCategory(Category category);
    Category getCategoryById(int id);
    List<Category> getAllCategories();
}
