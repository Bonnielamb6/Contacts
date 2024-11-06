package contacts;

import util.ContactsUtil;

import java.util.EnumSet;

public class Person extends Contact {
    private String surname;
    private String gender;
    private String birthDate;

    public Person() {
        super();
    }

    @Override
    public EnumSet<EditableFields> getFields() {
        return EnumSet.allOf(EditableFields.class);
    }

    @Override
    public String getName() {
        return name + " " + surname;
    }

    @Override
    public void setFieldByName(String field, String newValue) {
        EditableFields editableField = EditableFields.valueOf(field.toUpperCase());
        switch (editableField) {
            case NAME:
                setName(newValue);
                break;
            case SURNAME:
                setSurname(newValue);
                break;
            case GENDER:
                setGender(newValue);
                break;
            case BIRTHDATE:
                setBirthDate(newValue);
                break;
            case NUMBER:
                setNumber(newValue);
                break;
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = ContactsUtil.checkDateFormat(birthDate);
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "Surname: " + surname + '\n' +
                "Birth date: " + birthDate + '\n' +
                "Gender: " + gender + '\n' +
                "Number: " + number + '\n' +
                "Time created: " + timeCreated + '\n' +
                "Time last edit: " + timeModified + '\n';
    }

    public enum EditableFields {
        NAME, SURNAME, BIRTHDATE, GENDER, NUMBER
    }

}
