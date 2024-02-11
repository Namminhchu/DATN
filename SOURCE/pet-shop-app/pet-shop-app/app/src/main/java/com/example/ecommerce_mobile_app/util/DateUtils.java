package com.example.ecommerce_mobile_app.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private DateUtils() {
        // do nothing
    }

    private static final String PATTERN_FORMAT = "HH:mm dd/MM/yyyy";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String toString(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());

        String formattedInstant = formatter.format(instant);

        return formattedInstant;
    }
}
