package com.ohih.scheduler.scheduler;

import com.ohih.scheduler.webConstant.UrlConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class SchedulerController {

    @GetMapping(UrlConst.SCHEDULER)
    public String scheduler() {
        return "scheduler";
    }
}
