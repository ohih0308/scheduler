package com.ohih.scheduler.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohih.scheduler.config.JacksonConfig;
import com.ohih.scheduler.scheduler.dto.EventRequest;
import com.ohih.scheduler.webConstant.UrlConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SchedulerController {
    private final ScheduleService scheduleService;
    private final JacksonConfig jacksonConfig;

    @GetMapping(UrlConst.SCHEDULER)
    public String getSchedulerPage(@RequestParam("month") int month, @RequestParam("year") int year, Model model) {
        LocalDate date = LocalDate.of(year, month, 1);
        List<EventRequest> events = scheduleService.getEventsByMonth(date);

        // Jackson은 LocalDate, LocalTime을 JSON으로 변환 하지 못하기 떄문에 String으로 변환시켜 보낸다
        try {
            ObjectMapper objectMapper = jacksonConfig.objectMapper();
            String stringfiedEvents = objectMapper.writeValueAsString(events);
            model.addAttribute("eventsString", stringfiedEvents);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "scheduler";
    }
}