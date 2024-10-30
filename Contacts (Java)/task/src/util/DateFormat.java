package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormat {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public static String convertToDate(String birthDate) {
        try {
            LocalDate date = LocalDate.parse(birthDate, formatter);
            return date.toString();
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
        }
        return "[no data]";
    }

}
