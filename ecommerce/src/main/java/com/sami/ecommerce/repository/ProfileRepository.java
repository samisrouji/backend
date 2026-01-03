package com.sami.ecommerce.repository;

import com.sami.ecommerce.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {}
