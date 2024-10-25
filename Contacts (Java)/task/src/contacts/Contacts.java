package contacts;

public class Contacts {
    private String name;
    private String surname;
    private String number;
    private final static String REGEX_NUMBER = "^\\+?([\\da-zA-Z]{1,}[\\s-]?)?(\\([\\da-zA-Z]{2,}" +
            "(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$";

    public Contacts() {

    }

    public Contacts(String name, String surname, String number) {
        this.name = name;
        this.surname = surname;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        if(number.isBlank()){
            return "[no number]";
        }
        return number;
    }

    public void setNumber(String number) {
        if(number.matches(REGEX_NUMBER)){
            this.number = number;
        }else{
            System.out.println("Wrong number format!");
            this.number ="";
        }

    }
}
