package com.ohih.scheduler.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    int responseCode;
    String redirectUrl;
}
