package contacts;

import java.util.EnumSet;

public class Company extends Contact {
    private String address;

    public Company() {
        super();
    }

    @Override
    public EnumSet<EditableFields> getFields() {
        return EnumSet.allOf(EditableFields.class);
    }

    @Override
    public void setFieldByName(String field, String newValue) {
        EditableFields editableField = EditableFields.valueOf(field.toUpperCase());
        switch (editableField) {
            case ADDRESS:
                address = newValue;
                break;
            case NAME:
                setName(newValue);
                break;
            case NUMBER:
                number = newValue;
        }
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

    public enum EditableFields {
        NAME, ADDRESS, NUMBER
    }

}
