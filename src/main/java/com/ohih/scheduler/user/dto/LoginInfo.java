package com.ohih.scheduler.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInfo {
    private int id;
    private String username;
    private String email;
    private byte position;
}
