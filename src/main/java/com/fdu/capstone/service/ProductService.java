package com.fdu.capstone.service;

import com.fdu.capstone.model.Product;
import com.fdu.capstone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    public List<Product> searchProductsByName(String productName) {
        return productRepository.findByProductNameContaining(productName);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsBySellerId(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProductStatus(Long productId, String status) {
        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid product status");
        }

        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setProductStatus(status);
            return productRepository.save(product);
        } else {
            return null;
        }
    }

    private boolean isValidStatus(String status) {
        return "AVAILABLE".equals(status) || "REMOVED".equals(status) || "SOLD".equals(status);
    }

}



