package contacts;

import java.io.Serializable;

public class Company extends Contacts implements Serializable {
    private String address;

    public Company() {
        super();
    }

    public Company(String address) {
        this.address = address;
    }

    public Company(String name, String number, String address) {
        super(name, number);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Organization name: " + name + "\n" +
                "Address: " + address + '\n' +
                "Number: " + number + '\n' +
                "Time created: " + timeCreated + '\n' +
                "Time last edit: " + timeModified + '\n';
    }
}
