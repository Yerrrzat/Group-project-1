package controllers;

import controllers.interfaces.IBrandController;
import models.Brand;
import repositories.interfaces.IBrandRepository;

import java.util.List;

public class BrandController implements IBrandController {
    private final IBrandRepository brandRepository;

    public BrandController(IBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public String createBrand(String name, String description) {
        Brand brand = new Brand(name, description);
        boolean created = brandRepository.createBrand(brand);
        return created ? "Brand created successfully" : "Brand creation failed";
    }

    @Override
    public String getBrandById(int id) {
        Brand brand = brandRepository.getBrandById(id);
        return (brand == null) ? "Brand not found" : brand.toString();
    }

    @Override
    public String getAllBrands() {
        List<Brand> brands = brandRepository.getAllBrands();
        StringBuilder sb = new StringBuilder();
        for (Brand brand : brands) {
            sb.append(brand.toString()).append("\n");
        }
        return sb.toString();
    }
}
