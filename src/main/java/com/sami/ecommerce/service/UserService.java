package com.sami.ecommerce.service;

import com.sami.ecommerce.dto.UserDetailsDTO;
import com.sami.ecommerce.dto.UserEmailDTO;
import com.sami.ecommerce.entity.User;
import com.sami.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAllUsers() { return repository.findAll(); }

    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEmailDTO getByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserEmailDTO(user.getId(), user.getName(), user.getEmail());
    }
    
    public UserDetailsDTO getDetailsById(Long id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserDetailsDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getAddresses(),
            user.getProfile()
        );
    }

    public User create(User user) {
        if(repository.existsByEmail(user.getEmail()))
            throw new RuntimeException("Email already exists");
        return repository.save(user);
    }

    public User update(Long id, User updated) {
        User existing = getById(id);

        // Check email uniqueness on update
        if (!existing.getEmail().equals(updated.getEmail()) && repository.existsByEmail(updated.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setPassword(updated.getPassword());
        return repository.save(existing);
    }


    public void delete(Long id) {
        repository.deleteById(id); // cascade removes addresses, profile, carts
    }
}
