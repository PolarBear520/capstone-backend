package com.fdu.capstone.service;

import com.fdu.capstone.model.ProductCategory;
import com.fdu.capstone.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public ProductCategory getProductCategoryById(Long id) {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(id);
        return productCategoryOptional.orElse(null);
    }

    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    public void deleteProductCategory(Long id) {
        productCategoryRepository.deleteById(id);
    }
}
