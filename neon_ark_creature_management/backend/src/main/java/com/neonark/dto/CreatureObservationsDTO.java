package com.neonark.dto;

import java.util.List;

public class CreatureObservationsDTO {

    private Long id;
    private String name;
    private String habitatName;
    private List<ObservationDTO> observations;

    public CreatureObservationsDTO(
            Long id,
            String name,
            String habitatName,
            List<ObservationDTO> observations) {

        this.id = id;
        this.name = name;
        this.habitatName = habitatName;
        this.observations = observations;
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

    public List<ObservationDTO> getObservations() {
        return observations;
    }
}