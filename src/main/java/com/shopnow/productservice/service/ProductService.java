package com.shopnow.productservice.service;

import com.shopnow.productservice.dto.ProductRequest;
import com.shopnow.productservice.entity.Product;
import com.shopnow.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
