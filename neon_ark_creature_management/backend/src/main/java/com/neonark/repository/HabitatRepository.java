package com.neonark.repository;

import com.neonark.entity.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitatRepository extends JpaRepository<Habitat, Long> {
}