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
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            option = scanner.getUserInput();
            switch (option) {
                case "add":
                    System.out.println(directory.addContact()+"\n");
                    break;
                case "remove":
                    System.out.println(directory.removeContact()+"\n");
                    break;
                case "edit":
                    System.out.println(directory.edit()+"\n");
                    break;
                case "count":
                    System.out.println(directory.countContacts()+"\n");
                    break;
                case "info":
                    System.out.println(directory.contactsInfo()+"\n");
                    break;
                case "exit":
                    break;
            }
        } while (!option.equals("exit"));

    }

}
