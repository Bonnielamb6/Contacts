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
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            option = scanner.getUserInput();
            switch (option) {
                case "add":
                    System.out.println(directory.addContact()+"\n");
                    break;
                case "list":
                    directory.listContacts();
                    break;
                case "search":
                    directory.search();
                    break;
                case "count":
                    System.out.println(directory.countContacts()+"\n");
                    break;
                case "exit":
                    System.out.println();
                    break;
            }
        } while (!option.equals("exit"));
    }
}
