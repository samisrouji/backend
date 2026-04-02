package com.sami.ecommerce.service;

import com.sami.ecommerce.entity.Address;
import com.sami.ecommerce.repository.AddressRepository;
import com.sami.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address getById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public Address create(Address address) {
        // Verify user exists
        userRepository.findById(address.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return addressRepository.save(address);
    }

    public Address update(Long id, Address updated) {
        Address existing = getById(id);
        existing.setStreet(updated.getStreet());
        existing.setCity(updated.getCity());
        existing.setState(updated.getState());
        existing.setZip(updated.getZip());
        return addressRepository.save(existing);
    }

    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
