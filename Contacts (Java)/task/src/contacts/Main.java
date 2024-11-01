package contacts;

public class Main {
    public static void main(String[] args) {
        ScannerWrapper scanner = new ScannerWrapper();
        ContactManager directory = new ContactManager(args);
        Menu menu = new Menu(scanner,directory);
        menu.showMenu();
    }
}
