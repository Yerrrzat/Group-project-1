package controllers.interfaces;

import models.Brand;
import java.util.List;

public interface IBrandController {
    String createBrand(String name, String description);
    String getBrandById(int id);
    String getAllBrands();
}
