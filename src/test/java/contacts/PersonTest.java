package contacts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person personToTest;

    PersonTest() {
        personToTest = new Person();
        personToTest.setName("John");
        personToTest.setSurname("Doe");
        personToTest.setGender("M");
        personToTest.setNumber("777888111");
        personToTest.setBirthDate("05-05-2024");
        personToTest.setTimeModified();
        personToTest.setTimeCreated();
    }


    @Test
    void setPersonFieldByName() {
        String expectedBefore = """
                Name: John
                Surname: Doe
                Birth date: 2024-05-05
                Gender: M
                Number: 777888111
                Time created:""" + " " + personToTest.getTimeCreated()
                + "\nTime last edit: " + personToTest.getTimeModified() + "\n";
        assertEquals(expectedBefore, personToTest.toString());

        personToTest.setFieldByName("NAME", "Jose");
        personToTest.setFieldByName("SURNAME", "Marcos");
        personToTest.setFieldByName("GENDER", "F");
        personToTest.setFieldByName("NUMBER", "11111111");
        personToTest.setFieldByName("BIRTHDATE", "05-05-2023");
        personToTest.setTimeModified();
        String expectedAfter = """
                Name: Jose
                Surname: Marcos
                Birth date: 2023-05-05
                Gender: F
                Number: 11111111
                Time created:""" + " " + personToTest.getTimeCreated()
                + "\nTime last edit: " + personToTest.getTimeModified() + "\n";
        assertEquals(expectedAfter, personToTest.toString());
    }
}