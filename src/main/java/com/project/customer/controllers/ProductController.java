package com.project.customer.controllers;
import com.project.customer.dto.ProductDto;
import com.project.customer.entity.Product;
import com.project.customer.service.FileUpload;
import com.project.customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.Map;

@RequestMapping("/product")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;


    //TODO image handling

    @Autowired
    private FileUpload fileUpload;
    @Value("${product.path.images}")
    private String imagePath;
    @PostMapping("/image/{productid}")
    public ResponseEntity<?> uploadImage(@PathVariable int productid , @RequestParam("product_image") MultipartFile file){
        System.out.println("in image uploading"+productid);
        Product product = this.productService.getProduct(productid);
        String imageName = null;

        try{

            String uploadImage = this.fileUpload.uploadImage(imagePath, file);
            product.setImg(uploadImage);
            ProductDto updatedProduct = this.productService.update(productid, product);
            return  new ResponseEntity<>(updatedProduct,HttpStatus.ACCEPTED);
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(Map.of("message","file not upload in server"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/getimage/{productid}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getProductImage(@PathVariable int productid){
        Product product = productService.getProduct(productid);

        try {
            byte[] imageBytes = fileUpload.downloadImage(product.getImg(),imagePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Set the content type of the response

            // Set the response body and headers
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Map.of("message", "Error downloading image"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //image handling

    @PostMapping("/addProduct")
    public Product createProduct(@RequestBody Product product){
        System.out.println(product.getName());
        System.out.println(product.getAddedDate());


        return productService.addProduct(product);
    }

    @GetMapping
    public ResponseEntity<?>getAllListProduct(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>removeProduct(@PathVariable Integer id){
        return new ResponseEntity<>(productService.deleteProduct(id),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product>getProductInfo(@PathVariable Integer id){
        return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ProductDto updateProduct(@PathVariable int id, @RequestBody Product product){
        return productService.update(id,product);
    }

    @GetMapping("/getByName/{name}")
    public Product getProductByName(@PathVariable  String name){
        return productService.getProductByName(name);
    }
}


