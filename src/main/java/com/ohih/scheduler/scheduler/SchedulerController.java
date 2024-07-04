package com.ohih.scheduler.scheduler;

import com.ohih.scheduler.config.JacksonConfig;
import com.ohih.scheduler.webConstant.UrlConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SchedulerController {
    private final ScheduleService scheduleService;
    private final JacksonConfig jacksonConfig;

    @GetMapping(UrlConst.SCHEDULER)
    public String getSchedulerPage() {
        return "scheduler";
    }
}