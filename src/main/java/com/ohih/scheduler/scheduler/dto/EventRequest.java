package com.ohih.scheduler.scheduler.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
public class EventRequest {
    private Long id;
    private String title;
    private Long authorId;
    private String authorName;
    private String location;
    private String description;
    private List<String> participants;
    private EventStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean allDay;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}