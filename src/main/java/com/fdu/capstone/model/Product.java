package com.fdu.capstone.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_status")
    private String productStatus;

    @Column(name = "upload_date")
    private String uploadDate;

    @Column(name = "seller_id")
    private Long sellerId;

    // 构造函数
    public Product() {
    }

    public Product(String description, double price, String productName, String productStatus, String uploadDate, Long sellerId) {
        this.description = description;
        this.price = price;
        this.productName = productName;
        this.productStatus = productStatus;
        this.uploadDate = uploadDate;
        this.sellerId = sellerId;
    }

    // Getter和Setter方法
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    // 新增的getId方法
    public Long getId() {
        return productId;
    }
}
