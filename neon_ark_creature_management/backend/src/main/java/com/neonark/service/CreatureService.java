package com.neonark.service;

import com.neonark.dto.CreatureDTO;
import com.neonark.dto.*;
import com.neonark.entity.Creature;
import com.neonark.entity.Habitat;
import com.neonark.repository.CreatureRepository;
import com.neonark.repository.HabitatRepository;
import com.neonark.repository.ObservationRepository;

import com.neonark.dto.CreatureObservationsDTO;
import com.neonark.dto.ObservationDTO;
import com.neonark.repository.ObservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CreatureService {

    @Autowired
    private CreatureRepository creatureRepository;

    @Autowired
    private HabitatRepository habitatRepository;

    @Autowired
    private ObservationRepository observationRepository;

    public List<CreatureDTO> getAllCreatures() {

        return creatureRepository.findByStatusNot("REMOVED")
                .stream()
                .map(c -> new CreatureDTO(
                        c.getId(),
                        c.getName(),
                        c.getHabitat().getName(),
                        c.getStatus()))
                .toList();
    }

    public CreatureDTO getCreature(Long id) {

        Creature c = creatureRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new CreatureDTO(
                c.getId(),
                c.getName(),
                c.getHabitat().getName(),
                c.getStatus());
    }

    public Creature createCreature(String name, Long habitatId) {

        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (creatureRepository.existsByName(name)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Habitat habitat = habitatRepository.findById(habitatId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Creature creature = new Creature();

        creature.setName(name);
        creature.setStatus("ACTIVE");
        creature.setHabitat(habitat);

        return creatureRepository.save(creature);
    }

    public Creature renameCreature(Long id, String newName) {

        Creature creature = creatureRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        creature.setName(newName);

        return creatureRepository.save(creature);
    }

    public Creature removeCreature(Long id) {

        Creature creature = creatureRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        creature.setStatus("REMOVED");

        return creatureRepository.save(creature);
    }

    public CreatureObservationsDTO getCreatureObservations(Long id) {

    Creature creature = creatureRepository.findById(id)
            .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND));

    List<ObservationDTO> observations =
            observationRepository.findByCreatureId(id)
                    .stream()
                    .map(o -> new ObservationDTO(
                            o.getNote(),
                            o.getUser().getFullName(),
                            o.getCreatedAt().toString()
                    ))
                    .toList();

    return new CreatureObservationsDTO(
            creature.getId(),
            creature.getName(),
            creature.getHabitat().getName(),
            observations
    );
    }
}