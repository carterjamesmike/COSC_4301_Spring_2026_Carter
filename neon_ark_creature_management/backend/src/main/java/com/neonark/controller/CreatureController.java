package com.neonark.controller;

import com.neonark.dto.CreatureDTO;
import com.neonark.dto.CreatureObservationsDTO;
import com.neonark.entity.Creature;
import com.neonark.service.CreatureService;

import com.neonark.dto.CreatureObservationsDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/creatures")
public class CreatureController {

    @Autowired
    private CreatureService service;

    @GetMapping
    public ResponseEntity<List<CreatureDTO>> getAll() {
        return ResponseEntity.ok(service.getAllCreatures());
    }

    @GetMapping("/{id}/observations")
    public ResponseEntity<CreatureObservationsDTO> getObservations(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCreatureObservations(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatureDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCreature(id));
    }

    @PostMapping
    public ResponseEntity<Creature> create(@RequestBody Map<String, String> request) {

        String name = request.get("name");
        Long habitatId = Long.parseLong(request.get("habitatId"));

        Creature created = service.createCreature(name, habitatId);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<Creature> rename(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {

        return ResponseEntity.ok(
                service.renameCreature(id, request.get("newName"))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {

        service.removeCreature(id);

        return ResponseEntity.ok("Creature marked as REMOVED");
    }
}