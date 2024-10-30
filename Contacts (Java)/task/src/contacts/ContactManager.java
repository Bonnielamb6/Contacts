package contacts;

import util.Serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private final ArrayList<Contact> contacts;
    private final String fileName;

    public ContactManager(String[] args) {
        this.fileName = (args.length > 0) ? args[0] : null;
        this.contacts = (fileName != null) ? loadContacts(fileName) : new ArrayList<>();
    }

    private ArrayList<Contact> loadContacts(String fileName) {
        try {
            Object obj = Serializer.deserialize(fileName);
            return (ArrayList<Contact>) obj;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading contacts: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveContacts() {
        if (fileName != null) {
            try {
                Serializer.serialize(contacts, fileName);
                System.out.println("Contact saved successfully.");
            } catch (IOException e) {
                System.err.println("Error saving contacts: " + e.getMessage());
            }
        }
    }

    public List<Contact> findMatches(String query) {
        List<Contact> matches = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.toString().toLowerCase().contains(query)) {
                matches.add(contact);
                System.out.println(matches.size() + ". " + contact.getName());
            }
        }
        return matches;
    }

    public void addContact(Contact contactToAdd) {
        contacts.add(contactToAdd);
    }

    public int countContacts() {
        return contacts.size();
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public String listContacts() {
        StringBuilder contactsList = new StringBuilder();
        for (int currentContact = 0; currentContact < contacts.size(); currentContact++) {
            contactsList.append(currentContact + 1).append(". ").append(contacts.get(currentContact).getName()).append("\n");
        }
        return contactsList.toString();
    }

    public void removeContact(Contact contactToRemove) {
        contacts.remove(contactToRemove);
    }

}
