package com.ohih.scheduler.webConstant;

import java.util.regex.Pattern;

public interface ValidationPattern {
    Pattern EMAIL_DOMAIN_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+" + EmailConst.EMAIL_DOMAIN_REGEX);
    Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+{}:;<>,.?~])[A-Za-z\\d!@#$%^&*()_+{}:;<>,.?~]{8,16}$");
    Pattern USERNAME_PATTERN = Pattern.compile("^[가-힣]{2,10}$");
    Pattern TITLE_PATTERN = Pattern.compile("^.{4,50}$");
    Pattern LOCATION_PATTERN = Pattern.compile("^.{0,100}$");
    Pattern DESCRIPTION_PATTERN = Pattern.compile("^.{0,500}$");
    Pattern PARTICIPANT_PATTERN = Pattern.compile("^.{0,100}$");
}
