package com.neonark.repository;

import com.neonark.entity.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreatureRepository extends JpaRepository<Creature, Long> {

    boolean existsByName(String name);

    List<Creature> findByStatusNot(String status);
}