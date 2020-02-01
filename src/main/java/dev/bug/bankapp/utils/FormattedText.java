package dev.bug.bankapp.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FormattedText {

    public static String text(String message, Object... values) {
        return java.text.MessageFormat.format(message, values);
    }
}
