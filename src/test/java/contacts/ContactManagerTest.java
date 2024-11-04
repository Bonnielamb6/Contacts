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
    Person personMock;
    Person personMock2;
    Person personMock3;
    PrintStream outMock;

    ContactManagerTest() {
        managerToTest = new ContactManager(new String[]{});
        personMock = Mockito.mock(Person.class);
        personMock2 = Mockito.mock(Person.class);
        personMock3 = Mockito.mock(Person.class);
        outMock = Mockito.mock(PrintStream.class);
    }

    @Test
    void findMatches() {
        String query = "piz";
        String expectedString1 = "1. Pizza jones";
        String expectedString2 = "2. manu pizza";
        List<Contact> expectedList = new ArrayList<>();
        expectedList.add(personMock);
        expectedList.add(personMock2);

        when(personMock.toString()).thenReturn("Pizza jones");
        when(personMock2.toString()).thenReturn("manu pizza");
        when(personMock3.toString()).thenReturn("mons pitza");
        when(personMock.getName()).thenReturn("Pizza jones");
        when(personMock2.getName()).thenReturn("manu pizza");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        System.setOut(outMock);

        managerToTest.addContact(personMock);
        managerToTest.addContact(personMock2);
        managerToTest.addContact(personMock3);

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
        managerToTest.addContact(personMock);
        assertEquals(after, managerToTest.countContacts());
    }

    @Test
    void countContacts() {
        int before = 0;
        int after = 2;
        assertEquals(before, managerToTest.countContacts());
        managerToTest.addContact(personMock);
        managerToTest.addContact(personMock);
        assertEquals(after, managerToTest.countContacts());
    }

    @Test
    void listContacts() {
        int before = 0;
        String expected = "1. John Doe\n";
        assertEquals(before, managerToTest.countContacts());
        managerToTest.addContact(personMock);
        when(personMock.getName()).thenReturn("John Doe");
        assertEquals(expected, managerToTest.listContacts());
    }

    @Test
    void removeContact() {
        int before = 0;
        int after = 1;
        assertEquals(before, managerToTest.countContacts());
        managerToTest.addContact(personMock);
        assertEquals(after, managerToTest.countContacts());
        managerToTest.removeContact(personMock);
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