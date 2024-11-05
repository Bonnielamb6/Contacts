package contacts;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class MenuTest {
    Menu menuToTest;
    ScannerWrapper scanner;
    ContactManager contactManagerSpy;
    PrintStream outMock;
    Person person;

    MenuTest() {
        scanner = Mockito.mock(ScannerWrapper.class);
        ContactManager realContactManager = new ContactManager(new String[]{});
        contactManagerSpy = Mockito.spy(realContactManager);
        outMock = Mockito.mock(PrintStream.class);
        menuToTest = new Menu(scanner, contactManagerSpy);
        person = new Person();
    }

    @Test
    void showMenuIsCalled() {
        when(scanner.getUserInput()).thenReturn("exit");
        String expected = "[menu] Enter action (add, list, search, count, exit): ";
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        System.setOut(outMock);
        menuToTest.showMenu();
        verify(outMock, times(1)).print(captor.capture());
        assertEquals(expected, captor.getAllValues().get(0));
    }

    @Test
    void menuAddPersonIsCalled() {
        when(scanner.getUserInput()).thenReturn("add").thenReturn("person").thenReturn("John").thenReturn("Doe")
                .thenReturn("2024-05-02").thenReturn("M").thenReturn("1111111").thenReturn("exit");
        ArgumentCaptor<Contact> captor = ArgumentCaptor.forClass(Contact.class);
        menuToTest.showMenu();
        verify(contactManagerSpy, times(1)).addContact(captor.capture());
        assertNotNull(captor.getValue());
    }

    @Test
    void menuAddCompanyIsCalled() {
        when(scanner.getUserInput()).thenReturn("add").thenReturn("company").thenReturn("Pizza hut")
                .thenReturn("Here").thenReturn("11111").thenReturn("exit");
        ArgumentCaptor<Contact> captor = ArgumentCaptor.forClass(Contact.class);
        menuToTest.showMenu();
        verify(contactManagerSpy, times(1)).addContact(captor.capture());
        assertNotNull(captor.getValue());
    }

    @Test
    void menuListBackIsCalled() {
        when(scanner.getUserInput()).thenReturn("list").thenReturn("back").thenReturn("exit");
        when(contactManagerSpy.listContacts()).thenReturn("");
        menuToTest.showMenu();
        verify(contactManagerSpy, times(1)).listContacts();
    }

    @Test
    void menuListMenuContactIsCalled() {
        contactManagerSpy.addContact(person);

        when(scanner.getUserInput()).thenReturn("list").thenReturn("1").thenReturn("menu").thenReturn("exit");
        when(contactManagerSpy.listContacts()).thenReturn("");
        when(contactManagerSpy.getContact(anyInt())).thenReturn(person);

        System.setOut(outMock);

        menuToTest.showMenu();

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(outMock, times(4)).print(captor.capture());
        List<String> captured = captor.getAllValues();

        assertEquals("[record] Enter action (edit, delete, menu): ", captured.get(2));
        assertEquals("[menu] Enter action (add, list, search, count, exit): ", captured.get(3));
        verify(contactManagerSpy, times(2)).getContact(anyInt());
    }

    @Test
    void menuListAgainIsCalled() {
        when(scanner.getUserInput()).thenReturn("list").thenReturn("list").thenReturn("back").thenReturn("exit");
        when(contactManagerSpy.listContacts()).thenReturn("");
        menuToTest.showMenu();
        verify(contactManagerSpy, times(2)).listContacts();
    }

    @Test
    void menuSearchContactEditIsCalled() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(person);
        when(scanner.getUserInput()).thenReturn("search").thenReturn("piz").thenReturn("1")
                .thenReturn("edit").thenReturn("name").thenReturn("Juan").thenReturn("menu").thenReturn("exit");
        when(contactManagerSpy.findMatches("piz")).thenReturn(contacts);

        menuToTest.showMenu();
        verify(contactManagerSpy, times(1)).findMatches("piz");
        verify(contactManagerSpy, times(2)).saveContacts();
    }

    @Test
    void menuSearchContactDeleteIsCalled() {
        List<Contact> contacts = new ArrayList<>();

        contacts.add(person);
        when(scanner.getUserInput()).thenReturn("search").thenReturn("piz").thenReturn("1")
                .thenReturn("delete").thenReturn("menu").thenReturn("exit");
        when(contactManagerSpy.findMatches("piz")).thenReturn(contacts);

        menuToTest.showMenu();
        verify(contactManagerSpy, times(1)).findMatches("piz");
        verify(contactManagerSpy, times(2)).saveContacts();
        verify(contactManagerSpy, times(1)).removeContact(person);
    }

    @Test
    void menuSearchContactMenuIsCalled() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(person);
        when(scanner.getUserInput()).thenReturn("search").thenReturn("piz")
                .thenReturn("menu").thenReturn("exit");
        when(contactManagerSpy.findMatches("piz")).thenReturn(contacts);

        menuToTest.showMenu();
        verify(contactManagerSpy, times(1)).findMatches("piz");
    }

    @Test
    void menuSearchBackIsCalled() {
        when(scanner.getUserInput()).thenReturn("search").thenReturn("piz").thenReturn("back").thenReturn("exit");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        System.setOut(outMock);

        menuToTest.showMenu();
        verify(outMock, times(3)).print(captor.capture());
        List<String> captured = captor.getAllValues();
        assertEquals("[search] Enter action ([number], back, again): ", captured.get(1));
        assertEquals("[menu] Enter action (add, list, search, count, exit): ", captured.get(2));
    }

    @Test
    void menuSearchAgainIsCalled() {
        when(scanner.getUserInput()).thenReturn("search").thenReturn("piz").thenReturn("again")
                .thenReturn("exit");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        System.setOut(outMock);

        menuToTest.showMenu();
        verify(outMock, times(4)).print(captor.capture());
        List<String> captured = captor.getAllValues();
        assertEquals("[search] Enter action ([number], back, again): ", captured.get(1));
        assertEquals("[search] Enter action ([number], back, again): ", captured.get(2));
        assertEquals("[menu] Enter action (add, list, search, count, exit): ", captured.get(3));
    }

    @Test
    void menuCountIsCalled() {
        when(contactManagerSpy.countContacts()).thenReturn(0);
        when(scanner.getUserInput()).thenReturn("count").thenReturn("exit");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        System.setOut(outMock);

        String expected = "The Phone Book has 0 records.\n";

        menuToTest.showMenu();

        verify(contactManagerSpy, times(1)).countContacts();
        verify(outMock).println(captor.capture());
        assertEquals(expected, captor.getValue());
    }

    @Test
    void editFieldDoesNotExistShouldNotThrowException() {
        contactManagerSpy.addContact(person);
        when(scanner.getUserInput()).thenReturn("list").thenReturn("1").thenReturn("edit")
                .thenReturn("jose").thenReturn("menu").thenReturn("exit");
        when(contactManagerSpy.listContacts()).thenReturn("");
        when(contactManagerSpy.getContact(anyInt())).thenReturn(person);
        menuToTest.showMenu();
    }

}