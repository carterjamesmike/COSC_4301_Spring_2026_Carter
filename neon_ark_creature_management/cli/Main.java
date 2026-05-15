import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            Menu.display();

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> ApiClient.listCreatures();
                case 2 -> ApiClient.viewCreature(scanner);
                case 3 -> ApiClient.createCreature(scanner);
                case 4 -> ApiClient.renameCreature(scanner);
                case 5 -> ApiClient.removeCreature(scanner);
                case 6 -> ApiClient.viewObservations(scanner);
                case 7 -> ApiClient.findFeedings(scanner);
                case 8 -> ApiClient.viewUsers();
                case 0 -> {
                    System.out.print("Confirm exit? (y/n): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("y")) {
                        System.exit(0);
                    }
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}