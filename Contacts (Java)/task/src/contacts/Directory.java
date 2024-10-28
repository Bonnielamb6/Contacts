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
        System.out.println("Enter the type (person, organization): ");
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
    }

    public String contactsInfo() {
        listContacts();
        System.out.println("Enter index to show info: ");
        int index = Integer.parseInt(scanner.getUserInput());
        return contacts.get(index-1).toString();
    }

    public String edit() {
        if (contacts.isEmpty()) {
            return "No records to edit!";
        }
        listContacts();
        System.out.println("Select a record: ");
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
        tempContact.setTimeModified();
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
        tempContact.setTimeModified();
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

}
