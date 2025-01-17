package contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.EnumSet;

public abstract class Contact implements Serializable {
    private final static String REGEX_NUMBER = "^\\+?([\\da-zA-Z]{1,}[\\s-]?)?(\\([\\da-zA-Z]{2,}" +
            "(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$";
    protected String name;
    protected String number;
    protected LocalDate timeCreated;
    protected LocalDate timeModified;
    public Contact() {}

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

    public LocalDate getTimeModified() {
        return timeModified;
    }

    public LocalDate getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated() {
        this.timeCreated = LocalDate.now();
    }
    public abstract EnumSet<?> getFields();

    public abstract void setFieldByName(String field, String newValue);

}
