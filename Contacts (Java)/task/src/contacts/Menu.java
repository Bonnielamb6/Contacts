package contacts;

import util.ContactsUtil;

import java.util.Arrays;
import java.util.List;

public class Menu {
    private final ScannerWrapper scanner;
    private final ContactManager contactManager;
    String REGEX_POSITIVE_INTEGER = "^[1-9][0-9]*$";

    public Menu(ScannerWrapper scanner, ContactManager directory) {
        this.scanner = scanner;
        this.contactManager = directory;
    }

    public void showMenu() {
        String option;
        do {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            option = scanner.getUserInput();
            switch (option) {
                case "add":
                    System.out.println(addContact() + "\n");
                    break;
                case "list":
                    listContacts();
                    break;
                case "search":
                    search();
                    break;
                case "count":
                    System.out.println("The Phone Book has " + contactManager.countContacts() + " records." + "\n");
                    break;
                case "exit":
                    System.out.println();
                    break;
            }
        } while (!option.equals("exit"));
    }

    private void listContacts() {
        System.out.println(contactManager.listContacts());
        System.out.println();
        System.out.print("[list] Enter action ([number], back): ");
        String action = scanner.getUserInput();
        if (action.matches(REGEX_POSITIVE_INTEGER)) {
            int tempContact = Integer.parseInt(action);
            System.out.println(contactManager.getContact(tempContact - 1));
            menuContact(contactManager.getContact(tempContact - 1));
        } else {
            if (!action.equals("back")) {
                listContacts();
            }
        }
    }

    private String addContact() {
        System.out.print("Enter the type (person, organization): ");
        if (scanner.getUserInput().equals("person")) {
            contactManager.addContact(createPerson());
        } else {
            contactManager.addContact(createCompany());
        }
        contactManager.saveContacts();
        return "The record added.";
    }

    private Contact createCompany() {
        Company contactToAdd = new Company();
        System.out.println("Enter the name of the company:");
        contactToAdd.setName(scanner.getUserInput());
        System.out.println("Enter the address:");
        contactToAdd.setAddress(scanner.getUserInput());
        System.out.println("Enter the number:");
        contactToAdd.setNumber(scanner.getUserInput());
        initializeContact(contactToAdd);
        return contactToAdd;
    }

    private Contact createPerson() {
        Person contactToAdd = new Person();
        System.out.print("Enter the name of the person: ");
        contactToAdd.setName(scanner.getUserInput());
        System.out.print("Enter the surname of the person: ");
        contactToAdd.setSurname(scanner.getUserInput());
        System.out.print("Enter the birth date: ");
        contactToAdd.setBirthDate(scanner.getUserInput());
        System.out.print("Enter the gender: ");
        contactToAdd.setGender(ContactsUtil.isGenderCorrect(scanner.getUserInput()));
        System.out.print("Enter the number: ");
        contactToAdd.setNumber(scanner.getUserInput());
        initializeContact(contactToAdd);
        return contactToAdd;
    }

    private void initializeContact(Contact contact) {
        contact.setTimeCreated();
        contact.setTimeModified();
    }

    private void menuContact(Contact tempContact) {
        String action;
        do {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            action = scanner.getUserInput();
            switch (action.toLowerCase()) {
                case "edit" -> editContact(tempContact);
                case "delete" -> contactManager.removeContact(tempContact);
                case "menu" -> System.out.println();
            }
            contactManager.saveContacts();
        } while (!action.equalsIgnoreCase("menu"));
    }

    private void editContact(Contact contact) {
        editField(contact);
        contact.setTimeModified();
        System.out.println("Saved\n" + contact);
    }

    private void editField(Contact contact) {
        System.out.println("Select a field: " + Arrays.toString(contact.getFields()));
        String fieldChosen = scanner.getUserInput().toLowerCase();
        for (String field : contact.getFields()) {
            if (fieldChosen.equals(field)) {
                System.out.println("Enter " + field + ": ");
                String newValue = scanner.getUserInput();
                contact.setFieldByName(field, newValue);
                break;
            }
        }
    }

    public void search() {
        System.out.println("Enter search query: ");
        String searchQuery = scanner.getUserInput();
        List<Contact> matches = contactManager.findMatches(searchQuery);
        handleSearchAction(matches);
    }

    private void handleSearchAction(List<Contact> matches) {
        System.out.print("[search] Enter action ([number], back, again): ");
        String action = scanner.getUserInput();
        try {
            int index = Integer.parseInt(action) - 1;
            menuContact(matches.get(index));
        } catch (NumberFormatException e) {
            if (action.equalsIgnoreCase("again")) search();
        }
    }

}