package com.sami.ecommerce.service;

import com.sami.ecommerce.entity.CartItem;
import com.sami.ecommerce.repository.CartItemRepository;
import com.sami.ecommerce.repository.CartRepository;
import com.sami.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository repository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public List<CartItem> getAll() { return repository.findAll(); }

    public CartItem getById(Long id) { return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("CartItem not found")); }

    public CartItem create(CartItem item) {
        cartRepository.findById(item.getCart().getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        productRepository.findById(item.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return repository.save(item);
    }

    public void delete(Long id) { repository.deleteById(id); }
}
