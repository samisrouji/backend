package com.sami.ecommerce.service;

import com.sami.ecommerce.entity.Product;
import com.sami.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getAllProducts(String category) {
        return category == null
                ? repository.findAll()
                : repository.findByCategory(category);
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product create(Product product) {
        product.setIsAvailable(product.getInventoryQuantity() > 0);
        return repository.save(product);
    }

    public Product update(Long id, Product updated) {
        Product existing = getById(id);
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        existing.setCategory(updated.getCategory());
        existing.setInventoryQuantity(updated.getInventoryQuantity());
        existing.setIsAvailable(existing.getInventoryQuantity() > 0);
        return repository.save(existing);
    }

    // Update isAvailable field for all products based on inventoryQuantity
    public void updateAvailabilityForAllProducts() {
    List<Product> products = repository.findAll();
    for (Product product : products) {
        product.setIsAvailable(product.getInventoryQuantity() > 0);
    }
    repository.saveAll(products);
}


    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        repository.deleteById(id);
    }
}

