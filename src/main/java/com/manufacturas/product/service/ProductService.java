package com.manufacturas.product.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manufacturas.product.model.Product;
import com.manufacturas.product.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        if (existingProduct == null) {
            throw new RuntimeException("Product not found");
        }
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStock(product.getStock());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void updateStock(Long id, Integer stock) {
        Product existingProduct = getProductById(id);
        if (existingProduct == null) {
            throw new RuntimeException("Product not found");
        }

        if (existingProduct.getStock() < stock) {
            throw new RuntimeException("Stock is not enough");
        }

        existingProduct.setStock(existingProduct.getStock() - stock);
        productRepository.save(existingProduct);
    }
}
