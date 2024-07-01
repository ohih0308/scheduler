package com.ohih.scheduler.session;

import com.ohih.scheduler.user.dto.LoginInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionManager {
    public static void login(HttpServletRequest request, LoginInfo loginInfo) {
        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_INFO, loginInfo);
    }
}
