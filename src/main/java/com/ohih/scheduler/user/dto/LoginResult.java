package com.ohih.scheduler.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResult {
    private int responseCode;
    private String redirectUrl;
    private LoginInfo loginInfo;
}
