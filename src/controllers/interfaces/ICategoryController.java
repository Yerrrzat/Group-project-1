package controllers.interfaces;

public interface ICategoryController {
    String createCategory(String name, String description);
    String getCategoryById(int id);
    String getAllCategories();
}
