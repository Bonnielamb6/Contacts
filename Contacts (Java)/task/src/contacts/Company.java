package contacts;

import java.util.EnumSet;

public class Company extends Contact {
    private String address;

    public Company() {
        super();
    }

    @Override
    public EnumSet<editableFields> getFields() {
        return EnumSet.allOf(editableFields.class);
    }

    @Override
    public void setFieldByName(String field, String newValue) {
        editableFields editableField = editableFields.valueOf(field);
        switch (editableField) {
            case editableFields.address:
                address = newValue;
                break;
            case editableFields.name:
                setName(newValue);
                break;
            case editableFields.number:
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

    public enum editableFields {
        name, address, number
    }

}
