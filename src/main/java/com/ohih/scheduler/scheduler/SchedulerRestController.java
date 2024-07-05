package com.ohih.scheduler.scheduler;

import com.ohih.scheduler.scheduler.dto.EventCreationRequest;
import com.ohih.scheduler.scheduler.dto.EventRequest;
import com.ohih.scheduler.user.dto.LoginInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public List<Integer> createEvent(@SessionAttribute("loginInfo") LoginInfo loginInfo, @RequestBody EventCreationRequest event) {
        return scheduleService.createEvent(loginInfo.getId(), event);
    }
}