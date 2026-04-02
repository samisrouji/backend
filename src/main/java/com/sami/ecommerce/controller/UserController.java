package com.sami.ecommerce.controller;

import com.sami.ecommerce.dto.UserDetailsDTO;
import com.sami.ecommerce.dto.UserEmailDTO;
import com.sami.ecommerce.entity.User;
import com.sami.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<User> getAll() { return service.getAllUsers(); }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) { return service.getById(id); }

    @GetMapping("/{id}/details")
    public UserDetailsDTO getDetails(@PathVariable Long id) { return service.getDetailsById(id); }

    @GetMapping("/by-email")
    public UserEmailDTO getByEmail(@RequestParam String email) { return service.getByEmail(email); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) { return service.create(user); }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) { return service.update(id, user); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
