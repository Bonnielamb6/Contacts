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
        this.fileName = (args.length > 0) ? args[0] : null;
        this.contacts = (fileName != null) ? loadContacts(fileName) : new ArrayList<>();
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

    private void saveContacts() {
        if (fileName != null) {
            try {
                Serialization.serialize(contacts, fileName);
                System.out.println("Contacts saved successfully.");
            } catch (IOException e) {
                System.err.println("Error saving contacts: " + e.getMessage());
            }
        }
    }

    public String addContact() {
        System.out.print("Enter the type (person, organization): ");
        if (scanner.getUserInput().equals("person")) {
            contacts.add(createPerson());
        } else {
            addCompany();
        }
        saveContacts();
        return "The record added.";
    }

    private Person createPerson() {
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
        initializeContact(contactToAdd, true);
        return contactToAdd;
    }

    private void initializeContact(Contacts contact, boolean isPerson) {
        contact.setPerson(isPerson);
        contact.setTimeCreated();
        contact.setTimeModified();
    }


    public String countContacts() {
        return "The Phone Book has " + contacts.size() + " records.";
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
            switch (action.toLowerCase()) {
                case "edit" -> editContact(tempContact);
                case "delete" -> contacts.remove(tempContact);
                case "menu" -> System.out.println();
            }
            saveContacts();
        } while (!action.equalsIgnoreCase("menu"));
    }

    private void editContact(Contacts contact) {
        if (contact.isPerson()) {
            editField(contact, PersonField.values());
        } else {
            editField(contact, CompanyField.values());
        }
        contact.setTimeModified();
        System.out.println("Saved\n" + contact);
    }

    private void editField(Contacts contact, FieldOption[] fields) {
        System.out.println("Select a field: " + FieldOption.getOptions(fields));
        String fieldChosen = scanner.getUserInput().toLowerCase();
        for (FieldOption field : fields) {
            if (fieldChosen.equals(field.getName())) {
                System.out.println("Enter " + field.getName() + ": ");
                String newValue = scanner.getUserInput();
                field.update(contact, newValue);
                break;
            }
        }
    }

    private void addCompany() {
        Company contactToAdd = new Company();
        System.out.println("Enter the name of the company:");
        contactToAdd.setName(scanner.getUserInput());
        System.out.println("Enter the address:");
        contactToAdd.setAddress(scanner.getUserInput());
        System.out.println("Enter the number:");
        contactToAdd.setNumber(scanner.getUserInput());
        initializeContact(contactToAdd, false);
        contacts.add(contactToAdd);
    }

    public void search() {
        System.out.println("Enter search query: ");
        String searchQuery = scanner.getUserInput();
        List<Contacts> matches = findMatches(searchQuery);
        handleSearchAction(matches);
    }

    private List<Contacts> findMatches(String query) {
        List<Contacts> matches = new ArrayList<>();
        for (Contacts contact : contacts) {
            if (contact.toString().toLowerCase().contains(query)) {
                matches.add(contact);
                if (contact.isPerson) {
                    System.out.println(matches.size() + ". " + contact.getName() + " " + ((Person) contact).getSurname());
                } else {
                    System.out.println(matches.size() + ". " + contact.getName());
                }
            }
        }
        return matches;
    }

    private void handleSearchAction(List<Contacts> matches) {
        System.out.print("[search] Enter action ([number], back, again): ");
        String action = scanner.getUserInput();
        try {
            int index = Integer.parseInt(action) - 1;
            menuContact(matches.get(index));
        } catch (NumberFormatException e) {
            if (action.equalsIgnoreCase("again")) search();
        }
    }

    private interface FieldOption {
        String getName();

        void update(Contacts contact, String newValue);

        static String getOptions(FieldOption[] options) {
            StringBuilder builder = new StringBuilder();
            for (FieldOption option : options) {
                builder.append(option.getName()).append(", ");
            }
            return builder.substring(0, builder.length() - 2);
        }
    }

    private enum PersonField implements FieldOption {
        NAME("name") {
            public void update(Contacts contact, String newValue) {
                contact.setName(newValue);
            }
        },
        SURNAME("surname") {
            public void update(Contacts contact, String newValue) {
                ((Person) contact).setSurname(newValue);
            }
        },
        BIRTH("birth") {
            public void update(Contacts contact, String newValue) {
                ((Person) contact).setBirthDate(newValue);
            }
        },
        GENDER("gender") {
            public void update(Contacts contact, String newValue) {
                ((Person) contact).setGender(newValue);
            }
        },
        NUMBER("number") {
            public void update(Contacts contact, String newValue) {
                contact.setNumber(newValue);
            }
        };

        private final String name;

        PersonField(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private enum CompanyField implements FieldOption {
        NAME("name") {
            public void update(Contacts contact, String newValue) {
                contact.setName(newValue);
            }
        },
        ADDRESS("address") {
            public void update(Contacts contact, String newValue) {
                ((Company) contact).setAddress(newValue);
            }
        },
        NUMBER("number") {
            public void update(Contacts contact, String newValue) {
                contact.setNumber(newValue);
            }
        };

        private final String name;

        CompanyField(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
