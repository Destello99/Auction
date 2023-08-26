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
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public List<Product> getAllProduct() {
        List<Product>list= productRepository.findAll();
        List <Product>l1=list.stream().filter(p->p.isStatus()==false).collect(Collectors.toList());
        return l1;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    @Override
    public String deleteProduct(Integer id) {
        Product product=productRepository.findById(id).orElseThrow(()->new NoSuchResourceFound("no such element found"));
        productRepository.delete(product);
        return "product removed successfully";
    }

    @Override
    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElseThrow(()->new NoSuchResourceFound("no such element found"));
    }

    @Override
    public ProductDto update(Integer id, Product product) {
        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new NoSuchResourceFound("no such product"));
        oldProduct.setName(product.getName());
        oldProduct.setAddedDate(product.getAddedDate());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setImg(product.getImg());//new added
        oldProduct.setStatus(product.isStatus());
        productRepository.save(oldProduct);
        return mapper.map(oldProduct, ProductDto.class);
    }

    @Override
    public Product getProductByName(String name) {

        return productRepository.findProductByName(name).orElseThrow(()->new NoSuchResourceFound("no such element found"));
    }
}
