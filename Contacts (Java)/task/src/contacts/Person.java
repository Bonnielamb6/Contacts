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
            case EditableFields.NAME:
                setName(newValue);
                break;
            case EditableFields.SURNAME:
                setSurname(newValue);
                break;
            case EditableFields.GENDER:
                setGender(newValue);
                break;
            case EditableFields.BIRTHDATE:
                setBirthDate(newValue);
                break;
            case EditableFields.NUMBER:
                setNumber(newValue);
                break;
        }
    }

    public Person(String name, String surname, String number, String gender, String birthDate) {
        super(name, number);
        this.surname = surname;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Person(String surname, String gender, String birthDate) {
        this.surname = surname;
        this.gender = gender;
        this.birthDate = birthDate;
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
