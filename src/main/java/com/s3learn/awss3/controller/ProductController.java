package com.s3learn.awss3.controller;

import com.s3learn.awss3.entity.Product;
import com.s3learn.awss3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

   private final ProductService productService;

   @PostMapping
    public Product createProduct(@RequestBody Product product) {
       return  productService.createProduct(product);
   }

   @GetMapping
    public List<Product> getAllProducts() {
       return productService.getAllProducts();
   }

   @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
       return productService.getProductById(id);
   }

   @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
       productService.deleteProduct(productService.getProductById(id));
   }

   @PutMapping
    public Product updateProduct(@RequestBody Product product) {
       return productService.updateProduct(product);
   }
}
