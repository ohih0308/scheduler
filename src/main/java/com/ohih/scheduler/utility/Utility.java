package com.ohih.scheduler.utility;

import java.util.regex.Pattern;

public class Utility {
    public static void exceptionGenerator(int result) throws Exception {
        if (result != 1) {
            throw new Exception();
        }
    }

    public static boolean isValidated(Pattern pattern, String input) {
        if (input == null) {
            return false;
        }
        return pattern.matcher(input).matches();
    }
}
