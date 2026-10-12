package com.example.productservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.productservice.entity.Product;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() {

        Product product = new Product();

        product.setProductName("Laptop");
        product.setPrice(50000.0);

        when(repository.save(any(Product.class)))
                .thenReturn(product);

        Product saved =
                service.addProduct(product);

        assertEquals(
                "Laptop",
                saved.getProductName());
    }
    @Test
    void testGetProduct() {

        Product product = new Product();

        product.setId(1L);
        product.setProductName("Laptop");

        when(repository.findById(1L))
                .thenReturn(Optional.of(product));

        Product result =
                service.getProduct(1L);

        assertEquals(
                "Laptop",
                result.getProductName());
    }
    @Test
    void testGetProductNotFound() {

        when(repository.findById(100L))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> service.getProduct(100L));
    }
    @Test
    void testUpdateProduct() {

        Product existing = new Product();

        existing.setId(1L);
        existing.setProductName("Old Laptop");

        Product updated = new Product();

        updated.setProductName("New Laptop");
        updated.setDescription("Dell");
        updated.setPrice(70000.0);
        updated.setStock(10);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(repository.save(any(Product.class)))
                .thenReturn(existing);

        Product result =
                service.updateProduct(1L, updated);

        assertEquals(
                "New Laptop",
                result.getProductName());
    }
}