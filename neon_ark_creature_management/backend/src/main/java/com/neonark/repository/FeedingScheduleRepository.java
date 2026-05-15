package com.neonark.repository;

import com.neonark.entity.FeedingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface FeedingScheduleRepository
        extends JpaRepository<FeedingSchedule, Long> {

    List<FeedingSchedule> findByFeedingTime(LocalTime feedingTime);
}