package contacts;

public class Contacts {
    private String name;
    private String surname;
    private String number;

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
        this.number = number;
    }
}
