package com.ohih.scheduler.webConstant;

public interface ResponseCode {
    // Method Success  0~99
    int REGISTER_SUCCESS = 0;
    int LOGIN_SUCCESS = 1;
    int EVENT_CREATION_SUCCESS = 2;

    // Method Fail 100~199
    int LOGIN_FAILURE = 100;
    int EVENT_CREATION_FAILURE = 101;

    // Validation 200~299
    int REGISTER_IS_VALIDATED = 200;
    int LOGIN_IS_VALIDATED = 201;

    int EMAIL_DUPLICATION_ERROR = 220;
    int EMAIL_VALIDATION_ERROR = 221;
    int PASSWORD_VALIDATION_ERROR = 222;
    int USERNAME_VALIDATION_ERROR = 223;

}
