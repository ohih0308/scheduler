package com.ohih.scheduler.scheduler;

import com.ohih.scheduler.config.JacksonConfig;
import com.ohih.scheduler.scheduler.dto.Event;
import com.ohih.scheduler.scheduler.dto.EventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.ohih.scheduler.webConstant.UrlConst.CREATE_EVENT;
import static com.ohih.scheduler.webConstant.UrlConst.EVENTS_BY_MONTH;

@RestController
@RequiredArgsConstructor
public class SchedulerRestController {
    private final ScheduleService scheduleService;

    @PostMapping(EVENTS_BY_MONTH)
    public List<EventRequest> getEventList(@RequestParam("year") int year, @RequestParam("month") int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        return scheduleService.getEventsByMonth(date);
    }

    @PostMapping(CREATE_EVENT)
    public List<Integer> createEvent(@RequestBody Event event) {
        return scheduleService.createEvent(event);
    }
}