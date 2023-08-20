package com.project.customer.serviceImpl;

import com.project.customer.custome_exception.NoSuchResourceFound;
import com.project.customer.dto.ProductDto;
import com.project.customer.entity.Product;
import com.project.customer.repositories.ProductRepository;
import com.project.customer.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {


    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(p-> mapper.map(p, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getSingleProduct(int id) {
        Product byId = productRepository.findById(id).orElseThrow(()-> new NoSuchResourceFound("no such product"));
        return mapper.map(byId, ProductDto.class);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void removeProduct(int id) {
        Product byId = productRepository.findById(id).orElseThrow(()-> new NoSuchResourceFound("no such product"));
        productRepository.delete(byId);
    }

    @Override
    public ProductDto update(int id, Product product) {
        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new NoSuchResourceFound("no such product"));
        oldProduct.setName(product.getName());
        oldProduct.setAddedDate(product.getAddedDate());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setStatus(product.isStatus());
        productRepository.save(oldProduct);
        return mapper.map(oldProduct, ProductDto.class);
    }
}
