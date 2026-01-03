package com.sami.ecommerce.service;

import com.sami.ecommerce.entity.Profile;
import com.sami.ecommerce.repository.ProfileRepository;
import com.sami.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;
    private final UserRepository userRepository;

    public List<Profile> getAll() { return repository.findAll(); }

    public Profile getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public Profile create(Profile profile) {
        userRepository.findById(profile.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return repository.save(profile);
    }

    public Profile update(Long id, Profile updated) {
        Profile existing = getById(id);
        existing.setPhoneNumber(updated.getPhoneNumber());
        existing.setLoyaltyPoints(updated.getLoyaltyPoints());
        return repository.save(existing);
    }

    public void delete(Long id) { repository.deleteById(id); }
}
