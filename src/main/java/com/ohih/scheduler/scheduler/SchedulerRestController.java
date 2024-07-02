package com.ohih.scheduler.scheduler;

import com.ohih.scheduler.scheduler.dto.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SchedulerRestController {
    private final ScheduleService scheduleService;

    @PostMapping("/create-event")
    public List<Integer> createEvent(@RequestBody Event event) {
        return scheduleService.createEvent(event);
    }
}