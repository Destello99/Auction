package com.project.customer.service;

import com.project.customer.dto.ProductDto;
import com.project.customer.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductService{

    List<ProductDto> getAllProduct();
    ProductDto getSingleProduct(int id);

    Product addProduct(Product product);

    void removeProduct(int id);

    ProductDto update(int id, Product product);
}
