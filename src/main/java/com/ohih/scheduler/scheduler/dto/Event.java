package com.ohih.scheduler.scheduler.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
public class Event {
    private Long id;
    private String title;
    private Long authorId;
    private String location;
    private String description;
    private List<String> participants; // Assuming participants is a list of participant names or IDs
    private String status; // 'confirmed', 'tentative', 'cancelled'
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean allDay;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
