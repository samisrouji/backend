package com.sami.ecommerce.controller;

import com.sami.ecommerce.entity.Profile;
import com.sami.ecommerce.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;

    @GetMapping
    public List<Profile> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Profile get(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profile create(@RequestBody Profile profile) { return service.create(profile); }

    @PutMapping("/{id}")
    public Profile update(@PathVariable Long id, @RequestBody Profile profile) { return service.update(id, profile); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
