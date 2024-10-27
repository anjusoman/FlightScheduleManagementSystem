package personal.schedulingservice.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String[] convertToString(LocalDateTime dateTime) {
        String date = dateTime.format(DATE_FORMATTER);
        String time = dateTime.format(TIME_FORMATTER);
        return new String[]{date, time};
    }

    public static LocalDateTime convertToDateTime(String date, String time) {
        String combinedDateTime = date + " " + time;
        return LocalDateTime.parse(combinedDateTime, DATE_TIME_FORMATTER);
    }

}

