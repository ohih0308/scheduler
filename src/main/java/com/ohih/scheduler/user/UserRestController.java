package com.ohih.scheduler.user;

import com.ohih.scheduler.session.SessionManager;
import com.ohih.scheduler.user.dto.Login;
import com.ohih.scheduler.user.dto.LoginResult;
import com.ohih.scheduler.user.dto.UserResponse;
import com.ohih.scheduler.user.dto.Register;
import com.ohih.scheduler.webConstant.ResponseCode;
import com.ohih.scheduler.webConstant.UrlConst;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping(UrlConst.REGISTER)
    public UserResponse register(@RequestBody Register register) {
        return userService.register(register);
    }

    @PostMapping(UrlConst.LOGIN)
    public LoginResult login(HttpServletRequest request, @RequestBody Login login) {
        LoginResult loginResult = userService.login(login);

        if (loginResult.getResponseCode() == ResponseCode.LOGIN_SUCCESS) {
            SessionManager.login(request, loginResult.getLoginInfo());
        }
        return loginResult;
    }

    @PostMapping(UrlConst.LOGOUT)
    public UserResponse logout(HttpServletRequest request) {
        SessionManager.logout(request);
        return userService.logout();
    }
}
