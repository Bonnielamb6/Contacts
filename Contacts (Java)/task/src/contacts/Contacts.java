package contacts;

import java.time.LocalDate;

public class Contacts {
    private final static String REGEX_NUMBER = "^\\+?([\\da-zA-Z]{1,}[\\s-]?)?(\\([\\da-zA-Z]{2,}" +
            "(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$";
    protected String name;
    protected String number;
    protected LocalDate timeCreated;
    protected LocalDate timeModified;
    protected boolean isPerson;
    public Contacts() {

    }

    public Contacts(String name, String number) {
        this.name = name;
        this.number = number;
        timeCreated = LocalDate.now();
        timeModified = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        if (number.isBlank()) {
            return "[no number]";
        }
        return number;
    }

    public void setNumber(String number) {
        if (number.matches(REGEX_NUMBER)) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
            this.number = "";
        }

    }

    public void setTimeModified(){
        this.timeModified = LocalDate.now();
    }

    public boolean isPerson() {
        return isPerson;
    }

    public void setPerson(boolean person) {
        isPerson = person;
    }

    public LocalDate getTimeModified() {
        return timeModified;
    }


    public LocalDate getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated() {
        this.timeCreated = LocalDate.now();
    }
}
