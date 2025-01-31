package controllers.interfaces;

import models.Brand;

public interface IBrandController {
    String createBrand(String name, String description);
    String getBrandById(int id);
    String getAllBrands();
}
