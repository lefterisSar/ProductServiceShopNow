package com.shopnow.productservice.service;

import com.shopnow.productservice.dto.ProductRequest;
import com.shopnow.productservice.entity.Product;
import com.shopnow.productservice.exception.ResourceNotFoundException;
import com.shopnow.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service for managing products.
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    /**
     * Creates a new product.
     *
     * @param request the product request containing the product details
     * @return the created product
     */
    public Product createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
        return repository.save(product);
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    public List<Product> getAllProducts() {
        try {
            System.out.println("Fetching products...");
            List<Product> products = repository.findAll();
            System.out.println("Fetched products: " + products);
            return products;
        } catch (Exception e) {
            e.printStackTrace(); // 👈 THIS WILL PRINT EXACT ERROR
            throw new RuntimeException("Failed fetching products: " + e.getMessage());
        }
    }
    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the product with the specified ID
     * @throws ResourceNotFoundException if no product is found with the specified ID
     */
    public Product getProductById(UUID id) {
        return repository.findById((id))
                .orElseThrow(() -> new ResourceNotFoundException("Product",id));
    }
}
