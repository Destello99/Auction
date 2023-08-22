package com.project.customer.service;

import com.project.customer.dto.ProductDto;
import com.project.customer.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    Product addProduct(Product product);

    String deleteProduct(Integer id);

    Product getProduct(Integer id);
    ProductDto update(Integer id, Product product);


    Product getProductByName(String name);

}
