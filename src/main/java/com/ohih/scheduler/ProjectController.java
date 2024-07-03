package com.ohih.scheduler;

import com.ohih.scheduler.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.ohih.scheduler.webConstant.UrlConst.GET_MESSAGE;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final Utility utility;

    @ResponseBody
    @PostMapping(GET_MESSAGE)
    public String getMessage(@RequestBody int responseCode) {
        return utility.getMessage(responseCode);
    }
}