package com.ohih.scheduler.webConstant;

public interface ResponseCode {
    // Method Success  0~99
    int REGISTER_SUCCESS = 0;
    int LOGIN_SUCCESS = 1;
    int LOGOUT_SUCCESS = 2;
    int EVENT_CREATION_SUCCESS = 3;

    // Method Fail 100~199
    int LOGIN_FAILURE = 100;
    int EVENT_CREATION_FAILURE = 101;

    // Validation 200~299
    int REGISTER_IS_VALIDATED = 200;
    int LOGIN_IS_VALIDATED = 201;
    int EVENT_IS_VALIDATED = 202;

    int AUTHOR_ID_NULL_ERROR = 220;
    int EMAIL_DUPLICATION_ERROR = 221;
    int EMAIL_VALIDATION_ERROR = 222;
    int PASSWORD_VALIDATION_ERROR = 223;
    int USERNAME_VALIDATION_ERROR = 224;
    int START_DATE_VALIDATION_ERROR = 225;
    int END_DATE_VALIDATION_ERROR = 226;
    int DATE_ORDER_VALIDATION_ERROR = 227;
    int START_TIME_VALIDATION_ERROR = 228;
    int END_TIME_VALIDATION_ERROR = 229;
    int TIME_ORDER_VALIDATION_ERROR = 230;
    int ALLDAY_TIME_VALIDATION_ERROR = 231;
    int STATUS_VALIDATION_ERROR = 232;
    int TITLE_VALIDATION_ERROR = 233;
    int LOCATION_VALIDATION_ERROR = 234;
    int DESCRIPTION_VALIDATION_ERROR = 235;
    int PARTICIPANTS_VALIDATION_ERROR = 236;
    int START_DATE_TIME_PAST_ERROR = 237;
    int END_DATE_TIME_PAST_ERROR = 238;
    int TIME_NULL_ERROR = 239;
}
