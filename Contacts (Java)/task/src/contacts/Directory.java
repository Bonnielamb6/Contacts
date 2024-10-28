package contacts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private final List<Contacts> contacts;
    private final ScannerWrapper scanner;
    private final String fileName;

    public Directory(ScannerWrapper scanner, String[] args) {
        this.scanner = scanner;
        if (!(args.length == 0)) {
            fileName = args[0];
            contacts = loadContacts(fileName);
        } else {
            fileName = null;
            contacts = new ArrayList<>();
        }
    }

    private List<Contacts> loadContacts(String fileName) {
        try {
            Object obj = Serialization.deserialize(fileName);
            return (List<Contacts>) obj;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading contacts: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public String addContact() {
        System.out.print("Enter the type (person, organization): ");
        if (scanner.getUserInput().equals("person")) {
            addPerson();
        } else {
            addCompany();
        }
        return "The record added.";
    }

    private void addPerson() {
        Person contactToAdd = new Person();
        System.out.print("Enter the name of the person: ");
        contactToAdd.setName(scanner.getUserInput());
        System.out.print("Enter the surname of the person: ");
        contactToAdd.setSurname(scanner.getUserInput());
        System.out.print("Enter the birth date: ");
        contactToAdd.setBirthDate(scanner.getUserInput());
        System.out.print("Enter the gender: ");
        contactToAdd.setGender(scanner.getUserInput());
        System.out.print("Enter the number: ");
        contactToAdd.setNumber(scanner.getUserInput());
        contactToAdd.setTimeCreated();
        contactToAdd.setTimeModified();
        contactToAdd.setPerson(true);
        contacts.add(contactToAdd);
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

    public void listContacts() {
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println(i + 1 + ". " + contacts.get(i).getName());
        }
        System.out.println();
        System.out.print("[list] Enter action ([number], back): ");
        String action = scanner.getUserInput();
        try {
            int tempContact = Integer.parseInt(action);
            System.out.println(contacts.get(tempContact - 1));
            menuContact(contacts.get(tempContact - 1));

        } catch (NumberFormatException e) {
            if (!action.equals("back")) {
                listContacts();
            }
        }
    }

    private void menuContact(Contacts tempContact) {
        String action;
        do {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            action = scanner.getUserInput();
            switch (action) {
                case "edit":
                    if (tempContact.isPerson()) {
                        editPerson(tempContact);
                    } else {
                        editCompany(tempContact);
                    }
                    break;
                case "delete":
                    contacts.remove(tempContact);
                    break;
                case "menu":
                    System.out.println();
            }
        } while (!action.equals("menu"));
    }

    public String contactsInfo() {
        listContacts();
        System.out.println("Enter index to show info: ");
        int index = Integer.parseInt(scanner.getUserInput());
        return contacts.get(index - 1).toString();
    }

    public String edit() {
        if (contacts.isEmpty()) {
            return "No records to edit!";
        }
        listContacts();
        System.out.print("Select a record: ");
        int tempContact = Integer.parseInt(scanner.getUserInput());
        if (contacts.get(tempContact - 1).isPerson()) {
            editPerson(contacts.get(tempContact - 1));
        } else {
            editCompany(contacts.get(tempContact - 1));
        }
        return "The record updated!";
    }

    private void editPerson(Contacts tempContact) {
        System.out.println("Select a field (name, surname, birth, gender, number): ");
        String fieldChosen = scanner.getUserInput();
        switch (fieldChosen) {
            case "name":
                System.out.println("Enter name: ");
                tempContact.setName(scanner.getUserInput());
                break;
            case "surname":
                System.out.println("Enter surname: ");
                ((Person) tempContact).setSurname(scanner.getUserInput());
                break;
            case "number":
                System.out.println("Enter number: ");
                tempContact.setNumber(scanner.getUserInput());
                break;
            case "birth":
                System.out.println("Enter birth: ");
                ((Person) tempContact).setBirthDate(scanner.getUserInput());
                break;
            case "gender":
                System.out.println("Enter gender: ");
                ((Person) tempContact).setGender(scanner.getUserInput());
                break;
        }
        System.out.println("Saved");
        tempContact.setTimeModified();
        System.out.println(tempContact);
    }

    private void editCompany(Contacts tempContact) {
        System.out.println("Select a field (name, address, number): ");
        String fieldChosen = scanner.getUserInput();
        switch (fieldChosen) {
            case "name":
                System.out.println("Enter name: ");
                tempContact.setName(scanner.getUserInput());
                break;
            case "address":
                System.out.println("Enter address: ");
                ((Company) tempContact).setAddress(scanner.getUserInput());
                break;
            case "number":
                System.out.println("Enter number: ");
                tempContact.setNumber(scanner.getUserInput());
                break;
        }
        System.out.println("Saved");
        tempContact.setTimeModified();
        System.out.println(tempContact);
    }

    private void addCompany() {
        Company contactToAdd = new Company();
        System.out.println("Enter the name of the company:");
        contactToAdd.setName(scanner.getUserInput());
        System.out.println("Enter the address:");
        contactToAdd.setAddress(scanner.getUserInput());
        System.out.println("Enter the number:");
        contactToAdd.setNumber(scanner.getUserInput());
        contactToAdd.setPerson(false);
        contactToAdd.setTimeCreated();
        contactToAdd.setTimeModified();
        contacts.add(contactToAdd);
    }

    public void search() {
        System.out.println("Enter search query: ");
        String searchQuery = scanner.getUserInput();
        int numberOfMatches = 0;
        List<Contacts> matchesList = new ArrayList<>();
        for (Contacts tempContact : contacts) {
            if (tempContact.toString().toLowerCase().contains(searchQuery.toLowerCase())) {
                numberOfMatches++;
                if (tempContact.isPerson()) {
                    System.out.println(numberOfMatches + ". " + tempContact.getName() + " " + ((Person) tempContact).getSurname());
                } else {
                    System.out.println(numberOfMatches + ". " + tempContact.getName());
                }

                matchesList.add(tempContact);
            }
        }
        System.out.println("[search] Enter action ([number], back, again): ");
        String action = scanner.getUserInput();
        try {
            int tempContact = Integer.parseInt(action);
            menuContact(matchesList.get(tempContact - 1));
        } catch (Exception e) {
            switch (action) {
                case "back":
                    break;
                case "again":
                    search();
            }
        }
    }

}
