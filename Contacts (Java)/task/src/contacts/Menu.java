package contacts;

public class Menu {
    private final ScannerWrapper scanner;
    private final Directory directory;

    public Menu(ScannerWrapper scanner, Directory directory) {
        this.scanner = scanner;
        this.directory = directory;
    }

    public void showMenu() {
        String option;
        do {
            System.out.print("Enter action (add, remove, edit, count, list, exit): ");
            option = scanner.getUserInput();
            switch (option) {
                case "add":
                    System.out.println(directory.addContact());
                    break;
                case "remove":
                    System.out.println(directory.removeContact());
                    break;
                case "edit":
                    System.out.println(directory.editContact());
                    break;
                case "count":
                    System.out.println(directory.countContacts());
                    break;
                case "list":
                    System.out.println(directory.listContacts());
                    break;
                case "exit":
                    break;
            }
        } while (!option.equals("exit"));

    }

}
