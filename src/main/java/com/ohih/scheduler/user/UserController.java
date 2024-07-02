package com.ohih.scheduler.user;

import com.ohih.scheduler.webConstant.EmailConst;
import com.ohih.scheduler.webConstant.UrlConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping(UrlConst.REGISTER)
    public String register(Model model) {
        model.addAttribute("emailSuffix", EmailConst.EMAIL_SUFFIX);
        return "register";
    }

    @GetMapping(UrlConst.LOGIN)
    public String login(Model model) {
        model.addAttribute("emailSuffix", EmailConst.EMAIL_SUFFIX);
        return "login";
    }
}
