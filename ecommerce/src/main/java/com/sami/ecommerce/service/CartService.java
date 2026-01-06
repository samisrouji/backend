package com.sami.ecommerce.service;

import com.sami.ecommerce.dto.CartDTO;
import com.sami.ecommerce.dto.CartItemDTO;
import com.sami.ecommerce.entity.Cart;
import com.sami.ecommerce.entity.CartItem;
import com.sami.ecommerce.repository.CartRepository;
import com.sami.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository repository;
    private final UserRepository userRepository;

    public List<Cart> getAll() { return repository.findAll(); }

    public Cart getById(Long id) { return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cart not found")); }

        public CartDTO getCartDetailsById(Long id) {
        Cart cart = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItemDTO> items = cart.getCartItems().stream()
                .map(this::toCartItemDTO)
                .collect(Collectors.toList());

        return new CartDTO(cart.getId(), cart.getCreatedAt(), items);
    }
    public List<CartDTO> getCartsByUserId(Long userId) {
        List<Cart> carts = repository.findByUserId(userId);
        return carts.stream()
            .map(this::toCartDTO)
            .collect(Collectors.toList());
    }

    private CartDTO toCartDTO(Cart cart) {
        List<CartItemDTO> items = cart.getCartItems().stream()
            .map(this::toCartItemDTO)
            .collect(Collectors.toList());
        return new CartDTO(cart.getId(), cart.getCreatedAt(), items);
    }
    
    private CartItemDTO toCartItemDTO(CartItem item) {
        return new CartItemDTO(
            item.getId(),
            item.getProduct().getName(),
            item.getProduct().getPrice().doubleValue(),
            item.getQuantity()
        );
    }

    public Cart create(Cart cart) {
        userRepository.findById(cart.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return repository.save(cart);
    }

    public void delete(Long id) { repository.deleteById(id); }
}
