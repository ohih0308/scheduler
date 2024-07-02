package com.ohih.scheduler;

import com.ohih.scheduler.utility.Utility;
import com.ohih.scheduler.webConstant.UrlConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.ohih.scheduler.webConstant.UrlConst.GET_MESSAGE;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final Utility utility;

    @GetMapping(UrlConst.HOME)
    public String home() {
        return "home";
    }

    @ResponseBody
    @PostMapping(GET_MESSAGE)
    public String getMessage(@RequestBody int responseCode) {
        return utility.getMessage(responseCode);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}