package com.sami.ecommerce.controller;

import com.sami.ecommerce.dto.CartDTO;
import com.sami.ecommerce.entity.Cart;
import com.sami.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @GetMapping
    public List<Cart> getAll() { return service.getAll(); }

    @GetMapping("/{id}/details")
    public CartDTO getCartDetails(@PathVariable Long id) {
        return service.getCartDetailsById(id);
    }

    @GetMapping("/{id}")
    public Cart get(@PathVariable Long id) { return service.getById(id); }

    @GetMapping("/by-user/{userId}")
    public List<CartDTO> getCartsByUser(@PathVariable Long userId) {
        return service.getCartsByUserId(userId);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cart create(@RequestBody Cart cart) { return service.create(cart); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
