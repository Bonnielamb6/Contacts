package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ContactsUtil {
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

    public static String isGenderCorrect(String gender) {
        if (gender.isBlank()) {
            System.out.println("Bad gender!");
            return "[no data]";
        }
        char firstGenderLetter = gender.charAt(0);
        return switch (firstGenderLetter) {
            case 'M', 'F' -> "" + firstGenderLetter;
            default -> {
                System.out.println("Bad gender!");
                yield "[no data]";
            }
        };

    }

}
