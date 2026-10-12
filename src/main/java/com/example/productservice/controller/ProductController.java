package com.example.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @Operation(summary = "Add Product")
    @PostMapping
    public Product createProduct(
            @Valid @RequestBody Product product) {

        return service.addProduct(product);
    }

    @Operation(summary = "Update Product")
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {

        return service.updateProduct(id, product);
    }
    @Operation(summary = "Get Product By Id")
    @GetMapping("/{id}")
    public Product getProduct(
            @PathVariable Long id) {

        return service.getProduct(id);
    }
}
