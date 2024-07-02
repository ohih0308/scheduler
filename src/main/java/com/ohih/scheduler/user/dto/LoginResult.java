package com.ohih.scheduler.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginResult {
    private int responseCode;
    private String redirectUrl;
    private LoginInfo loginInfo;
}
