package com.neonark.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "feeding_schedules")
public class FeedingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime feedingTime;

    @ManyToOne
    @JoinColumn(name = "creature_id")
    private Creature creature;

    public Long getId() {
        return id;
    }

    public LocalTime getFeedingTime() {
        return feedingTime;
    }

    public void setFeedingTime(LocalTime feedingTime) {
        this.feedingTime = feedingTime;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }
}