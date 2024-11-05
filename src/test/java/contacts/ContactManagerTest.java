package contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import util.Serializer;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ContactManagerTest {

    ContactManager managerToTest;
    PrintStream outMock;
    Person person;
    Company company1;
    Company company2;
    Company company3;

    ContactManagerTest() {
        managerToTest = new ContactManager(new String[]{});
        outMock = Mockito.mock(PrintStream.class);
        person = new Person();
        company1 = new Company();
        company2 = new Company();
        company3 = new Company();
        person.setName("John");
        person.setSurname("Doe");
        company1.setName("Pizza jones");
        company2.setName("manu pizza");
        company3.setName("mons pitza");
    }

    @Test
    void findMatches() {
        String query = "piz";
        String expectedString1 = "1. Pizza jones";
        String expectedString2 = "2. manu pizza";
        List<Contact> expectedList = new ArrayList<>();

        expectedList.add(company1);
        expectedList.add(company2);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        System.setOut(outMock);

        managerToTest.addContact(company1);
        managerToTest.addContact(company2);
        managerToTest.addContact(company3);

        assertEquals(expectedList, managerToTest.findMatches(query));

        verify(outMock, times(2)).println(captor.capture());
        List<String> captured = captor.getAllValues();
        Assertions.assertEquals(expectedString1, captured.get(0));
        Assertions.assertEquals(expectedString2, captured.get(1));

    }

    @Test
    void addContact() {
        int before = 0;
        int after = 1;
        assertEquals(before, managerToTest.countContacts());
        managerToTest.addContact(person);
        assertEquals(after, managerToTest.countContacts());
    }

    @Test
    void countContacts() {
        int before = 0;
        int after = 2;
        assertEquals(before, managerToTest.countContacts());
        managerToTest.addContact(person);
        managerToTest.addContact(person);
        assertEquals(after, managerToTest.countContacts());
    }

    @Test
    void listContacts() {
        int before = 0;
        String expected = "1. John Doe\n";
        assertEquals(before, managerToTest.countContacts());
        managerToTest.addContact(person);
        assertEquals(expected, managerToTest.listContacts());
    }

    @Test
    void removeContact() {
        int before = 0;
        int after = 1;
        assertEquals(before, managerToTest.countContacts());
        managerToTest.addContact(person);
        assertEquals(after, managerToTest.countContacts());
        managerToTest.removeContact(person);
        assertEquals(before, managerToTest.countContacts());
    }

    @Test
    void loadContactsShouldNotThrowException() throws IOException, ClassNotFoundException {
        List<Contact> expectedList = new ArrayList<>();
        String[] args = {"contactsTest.txt"};
        try (MockedStatic<Serializer> mocked = Mockito.mockStatic(Serializer.class)) {
            when(Serializer.deserialize("contactsTest.txt")).thenReturn(expectedList);
            managerToTest = new ContactManager(args);
            mocked.verify(() -> Serializer.deserialize("contactsTest.txt"));
        }
    }

    @Test
    void saveContactsShouldNotThrowException() {
        managerToTest = new ContactManager(new String[]{"contactsTest.txt"});
        assertDoesNotThrow(() -> managerToTest.saveContacts());
    }

}