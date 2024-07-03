package com.ohih.scheduler.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginInfo {
    private int id;
    private String username;
    private String email;
    private byte position;
    private String department;
}
