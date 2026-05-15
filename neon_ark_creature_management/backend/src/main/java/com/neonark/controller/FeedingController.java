package com.neonark.controller;

import com.neonark.dto.FeedingCreatureDTO;
import com.neonark.service.FeedingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedings")
public class FeedingController {

    @Autowired
    private FeedingService service;

    @GetMapping
    public ResponseEntity<List<FeedingCreatureDTO>>
    getFeedings(@RequestParam String time) {

        return ResponseEntity.ok(
                service.getCreaturesToFeed(time)
        );
    }
}