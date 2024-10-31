package contacts;

import util.ContactsUtil;

import java.io.Serializable;
import java.util.Arrays;

public class Person extends Contact implements Serializable {
    private String surname;
    private String gender;
    private String birthDate;

    public Person() {
        super();
    }

    @Override
    public String[] getFields() {
        return Arrays.stream(editableFields.values()).map(Enum::name).toArray(String[]::new);
    }

    @Override
    public String getName() {
        return name + " " + surname;
    }

    @Override
    public void setFieldByName(String field, String newValue) {
        switch (field) {
            case "name":
                setName(newValue);
                break;
            case "surname":
                setSurname(newValue);
                break;
            case "gender":
                setGender(newValue);
                break;
            case "birthDate":
                setBirthDate(newValue);
                break;
            case "number":
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
        this.birthDate = ContactsUtil.convertToDate(birthDate);
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

    private enum editableFields {
        name, surname, birthDate, gender, number
    }

}
