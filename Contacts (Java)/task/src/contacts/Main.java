package contacts;

public class Main {
    public static void main(String[] args) {
        ScannerWrapper scanner = new ScannerWrapper();
        Directory directory = new Directory(scanner);
        Menu menu = new Menu(scanner,directory);
        menu.showMenu();
    }
}