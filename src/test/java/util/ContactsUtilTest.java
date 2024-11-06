package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactsUtilTest {

    @Test
    void checkDateFormatCorrect() {
        String inputDate = "05-03-2024";
        String expected = "2024-05-03";
        String actual = ContactsUtil.checkDateFormat(inputDate);
        assertEquals(expected, actual);
    }

    @Test
    void checkDateFormatIncorrect() {
        String inputDate = "12/22/37";
        String expected = "[no data]";
        String actual = ContactsUtil.checkDateFormat(inputDate);
        assertEquals(expected, actual);
    }

    @Test
    void isGenderCorrect() {
        String inputGender = "F";
        String expected = "F";
        assertEquals(expected, ContactsUtil.isGenderCorrect(inputGender));
    }

    @Test
    void isNotGenderCorrect() {
        String inputGender = "H";
        String expected = "[no data]";
        assertEquals(expected, ContactsUtil.isGenderCorrect(inputGender));
    }

    @Test
    void isGenderCorrectBlank() {
        String inputGender = "";
        String expected = "[no data]";
        assertEquals(expected, ContactsUtil.isGenderCorrect(inputGender));
    }
}