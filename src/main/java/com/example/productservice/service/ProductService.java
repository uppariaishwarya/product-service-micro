package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Long id,
                                 Product product) {

    	Product existing =
    	        repository.findById(id)
    	            .orElseThrow(() ->
    	                new ProductNotFoundException(
    	                        "Product not found with id : " + id));

        existing.setProductName(product.getProductName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());

        return repository.save(existing);
    }

    public Product getProduct(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id : " + id));
    }
}
