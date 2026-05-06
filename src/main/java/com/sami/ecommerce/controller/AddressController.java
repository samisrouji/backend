package com.sami.ecommerce.controller;

import com.sami.ecommerce.entity.Address;
import com.sami.ecommerce.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @GetMapping
    public List<Address> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Address get(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address create(@RequestBody Address address) { return service.create(address); }

    @PutMapping("/{id}")
    public Address update(@PathVariable Long id, @RequestBody Address address) { return service.update(id, address); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
