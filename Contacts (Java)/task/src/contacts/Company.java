package contacts;

import java.io.Serializable;
import java.util.Arrays;

public class Company extends Contact implements Serializable {
    private String address;

    public Company() {
        super();
    }

    @Override
    public String[] getFields() {
        return Arrays.stream(editableFields.values()).map(Enum::name).toArray(String[]::new);
    }

    @Override
    public void setFieldByName(String field, String newValue) {
        switch (field) {
            case "address":
                address = newValue;
                break;
            case "name":
                setName(newValue);
                break;
            case "number":
                number = newValue;
        }
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

    private enum editableFields {
        name, address, number
    }

}
