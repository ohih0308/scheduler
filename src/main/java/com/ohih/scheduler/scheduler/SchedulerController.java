package com.ohih.scheduler.scheduler;

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

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequiredArgsConstructor
public class SchedulerController {
    private final ScheduleService scheduleService;
    private final JacksonConfig jacksonConfig;

    @GetMapping(UrlConst.SCHEDULER)
    public String getSchedulerPage(@RequestParam("month") int month, @RequestParam("year") int year, Model model) {
        LocalDate date = LocalDate.of(year, month, 1);
        List<EventRequest> events = scheduleService.getEventsByMonth(date);

        try {
            ObjectMapper objectMapper = jacksonConfig.objectMapper();
            String eventsJson = objectMapper.writeValueAsString(events);
            model.addAttribute("eventsJson", eventsJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "scheduler";
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }
}