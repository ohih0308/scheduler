package com.ohih.scheduler.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class Utility {
    private final MessageSource messageSource;

    public boolean isValidated(Pattern pattern, String input) {
        if (input == null) {
            return false;
        }
        return pattern.matcher(input).matches();
    }

    public String getMessage(Integer responseCode) {
        return messageSource.getMessage(Integer.toString(responseCode), new Object[]{}, Locale.getDefault());
    }
}
