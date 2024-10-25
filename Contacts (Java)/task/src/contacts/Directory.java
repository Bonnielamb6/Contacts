package contacts;

import java.util.ArrayList;
import java.util.List;

public class Directory {
    private final List<Contacts> contacts = new ArrayList<>();
    private final ScannerWrapper scanner;

    public Directory(ScannerWrapper scanner) {
        this.scanner = scanner;
    }

    public String addContact() {
        Contacts contactToAdd = new Contacts();
        System.out.println("Enter the name of the person:");
        contactToAdd.setName(scanner.getUserInput());
        System.out.println("Enter the surname of the person:");
        contactToAdd.setSurname(scanner.getUserInput());
        System.out.println("Enter the number:");
        contactToAdd.setNumber(scanner.getUserInput());
        contacts.add(contactToAdd);
        return "The record added.";
    }

    public String countContacts() {
        return "The Phone Book has " + contacts.size() + " records.";
    }

    public String removeContact() {
        if (contacts.isEmpty()) {
            return "No records to remove!";
        }
        listContacts();
        System.out.println("Select a record: ");
        int tempContact = Integer.parseInt(scanner.getUserInput());
        contacts.remove(tempContact - 1);
        return "The record removed!";
    }

    public String listContacts() {
        StringBuilder listOfContacts = new StringBuilder();
        for (int i = 0; i < contacts.size(); i++) {
            listOfContacts.append(i + 1).append(". ")
                    .append(contacts.get(i).getName())
                    .append(" ").append(contacts.get(i).getSurname())
                    .append(", ").append(contacts.get(i).getNumber());
        }
        return listOfContacts.toString();
    }

    public String editContact() {
        if (contacts.isEmpty()) {
            return "No records to edit!";
        }
        listContacts();
        System.out.println("Select a record: ");
        int tempContact = Integer.parseInt(scanner.getUserInput());
        System.out.println("Select a field (name, surname, number): ");
        String fieldChosen = scanner.getUserInput();
        switch (fieldChosen) {
            case "name":
                System.out.println("Enter name: ");
                contacts.get(tempContact - 1).setName(scanner.getUserInput());
                break;
            case "surname":
                System.out.println("Enter surname: ");
                contacts.get(tempContact - 1).setSurname(scanner.getUserInput());
                break;
            case "number":
                System.out.println("Enter number: ");
                contacts.get(tempContact - 1).setNumber(scanner.getUserInput());
                break;
        }
        return "The record updated!";
    }
}
