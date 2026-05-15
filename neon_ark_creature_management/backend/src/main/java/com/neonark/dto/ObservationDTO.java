package com.neonark.dto;

public class ObservationDTO {

    private String note;
    private String author;
    private String timestamp;

    public ObservationDTO(
            String note,
            String author,
            String timestamp) {

        this.note = note;
        this.author = author;
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public String getAuthor() {
        return author;
    }

    public String getTimestamp() {
        return timestamp;
    }
}