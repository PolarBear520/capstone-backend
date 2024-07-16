package com.fdu.capstone.controller;

import com.fdu.capstone.model.ProductCategory;
import com.fdu.capstone.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productCategories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        ProductCategory createdProductCategory = productCategoryService.createProductCategory(productCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Long id) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        return productCategory != null ? ResponseEntity.ok(productCategory) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        return ResponseEntity.ok(productCategories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Long id) {
        try {
            productCategoryService.deleteProductCategory(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
