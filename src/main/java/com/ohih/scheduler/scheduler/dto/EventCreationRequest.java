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
public class EventCreationRequest {
    private String title;
    private Integer authorId;
    private String location;
    private String description;
    private List<String> participants;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean allDay;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
