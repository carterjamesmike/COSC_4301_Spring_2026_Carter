package com.neonark.service;

import com.neonark.dto.FeedingCreatureDTO;
import com.neonark.entity.FeedingSchedule;
import com.neonark.repository.FeedingScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;

@Service
public class FeedingService {

    @Autowired
    private FeedingScheduleRepository repository;

    public List<FeedingCreatureDTO> getCreaturesToFeed(String time) {

        try {

            LocalTime feedingTime = LocalTime.parse(time);

            return repository.findByFeedingTime(feedingTime)
                    .stream()
                    .map(fs -> new FeedingCreatureDTO(
                            fs.getCreature().getId(),
                            fs.getCreature().getName(),
                            fs.getCreature().getHabitat().getName(),
                            fs.getFeedingTime().toString()
                    ))
                    .toList();

        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid time format. Use HH:MM"
            );
        }
    }
}