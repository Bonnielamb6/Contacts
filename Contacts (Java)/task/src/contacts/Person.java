package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Person extends Contacts {
    private String surname;
    private String gender;
    private String birthDate;

    public Person() {
        super();
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
        if (!gender.isBlank() && isGenderCorrect(gender)) {
            this.gender = (gender);
        } else {
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        }
    }

    private boolean isGenderCorrect(String gender) {
        char firstGenderLetter = gender.charAt(0);
        return switch (firstGenderLetter) {
            case 'M', 'F' -> true;
            default -> false;

        };

    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = converToDate(birthDate);
    }

    private String converToDate(String birthDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate date = LocalDate.parse(birthDate, formatter);
            return date.toString();
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
        }
        return "[no data]";
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
}
