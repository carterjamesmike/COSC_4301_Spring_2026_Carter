package com.neonark.dto;

public class FeedingCreatureDTO {

    private Long id;
    private String name;
    private String habitatName;
    private String feedingTime;

    public FeedingCreatureDTO(
            Long id,
            String name,
            String habitatName,
            String feedingTime) {

        this.id = id;
        this.name = name;
        this.habitatName = habitatName;
        this.feedingTime = feedingTime;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHabitatName() {
        return habitatName;
    }

    public String getFeedingTime() {
        return feedingTime;
    }
}