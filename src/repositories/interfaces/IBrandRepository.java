package repositories.interfaces;

import models.Brand;
import java.util.List;

public interface IBrandRepository {
    boolean createBrand(Brand brand);
    Brand getBrandById(int id);
    List<Brand> getAllBrands();
}

