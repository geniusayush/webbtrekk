package com.sherlockcodes.webbtrekk.utils;

import com.sherlockcodes.webbtrekk.entity.Email;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

public class Utils {
    private static final Pattern emailPattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    private static final Pattern urlRegex = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#%?=~_|!:,.;/]*[-a-zA-Z0-9+&@#%=~_|]");

    public static void validateRequest(Email email) {
        if (!emailPattern.matcher(email.getTo()).matches() || !(email.getAttatchment().isEmpty() || urlRegex.matcher(email.getAttatchment()).matches())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
