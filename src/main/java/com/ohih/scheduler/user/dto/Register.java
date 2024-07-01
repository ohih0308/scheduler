package com.ohih.scheduler.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Register {
    private String email;
    private String password;
    private String username;
}
