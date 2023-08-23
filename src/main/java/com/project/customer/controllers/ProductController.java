package com.project.customer.controllers;


import com.project.customer.dto.ProductDto;
import com.project.customer.entity.Product;
import com.project.customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Adding product
    @PostMapping("/add")
    public Product createProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    //fetching all products
    @GetMapping("/all")
    public List<ProductDto> getAllProducts(){
         return productService.getAllProduct();
    }

    //getting product by id
    @GetMapping("/{id}")
    public  ProductDto getSingleProduct(@PathVariable int id){
        return productService.getSingleProduct(id);
    }

    //delete product
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.removeProduct(id);
        return "product removed";
    }

    //update product
    @PutMapping("/update/{id}")
    public ProductDto updateProduct(@PathVariable int id, @RequestBody Product product){
       return productService.update(id,product);
    }
}
