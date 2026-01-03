package com.sami.ecommerce.service;

import com.sami.ecommerce.entity.Cart;
import com.sami.ecommerce.repository.CartRepository;
import com.sami.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository repository;
    private final UserRepository userRepository;

    public List<Cart> getAll() { return repository.findAll(); }

    public Cart getById(Long id) { return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cart not found")); }

    public Cart create(Cart cart) {
        userRepository.findById(cart.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return repository.save(cart);
    }

    public void delete(Long id) { repository.deleteById(id); }
}
