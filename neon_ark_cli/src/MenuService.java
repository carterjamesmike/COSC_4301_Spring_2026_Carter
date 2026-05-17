import java.util.*;

public class MenuService {

    private Scanner scanner = new Scanner(System.in);
    private List<Warden> wardens;

    public MenuService() {
        wardens = CsvLoader.loadWardens("wardens.csv");
    }

    public void start() {

        boolean running = true;

        while (running) {

            printMainMenu();

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    addNewWarden();
                    break;

                case "2":
                    viewWardens();
                    break;

                case "3":
                    updateWardenMenu();
                    break;

                case "4":
                    manageCertificationsMenu();
                    break;

                case "5":
                    terminateWarden();
                    break;

                case "6":
                    running = false;
                    System.out.println("Exiting Neon Ark Console...");
                    break;

                default:
                    System.out.println("Invalid menu selection.");
            }
        }
    }

    private void printMainMenu() {

        System.out.println("\n=========================================================");
        System.out.println("NEON ARK - ADMIN WARDEN ONBOARDING CONSOLE");
        System.out.println("=========================================================");

        System.out.println("\n[ MAIN MENU ]");
        System.out.println("---------------------------------------------------------");
        System.out.println("1. Add New Warden");
        System.out.println("2. View Wardens");
        System.out.println("3. Update Warden");
        System.out.println("4. Manage Certifications");
        System.out.println("5. Deactivate / Terminate Warden");
        System.out.println("6. Exit");

        System.out.print("\nSelect Option: ");
    }

    private void addNewWarden() {

        System.out.println("\n=========================================================");
        System.out.println("ADD NEW WARDEN");
        System.out.println("=========================================================");

        String id;

        while (true) {
            System.out.print("Warden ID: ");
            id = scanner.nextLine();

            if (ValidationService.isBlank(id)) {
                System.out.println("Warden ID is required.");
                continue;
            }

            if (ValidationService.isDuplicateId(wardens, id)) {
                System.out.println("Duplicate Warden ID detected.");
                continue;
            }

            break;
        }

        String firstName;

        while (true) {
            System.out.print("First Name: ");
            firstName = scanner.nextLine();

            if (ValidationService.isBlank(firstName)) {
                System.out.println("First name cannot be blank.");
            } else {
                break;
            }
        }

        String lastName;

        while (true) {
            System.out.print("Last Name: ");
            lastName = scanner.nextLine();

            if (ValidationService.isBlank(lastName)) {
                System.out.println("Last name cannot be blank.");
            } else {
                break;
            }
        }

        String email;

        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine();

            if (ValidationService.isBlank(email)) {
                System.out.println("Email is required.");
                continue;
            }

            if (ValidationService.isDuplicateEmail(wardens, email)) {
                System.out.println("A Warden with this email already exists.");
                continue;
            }

            break;
        }

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        String hireDate;

        while (true) {
            System.out.print("Hire Date (YYYY-MM-DD): ");
            hireDate = scanner.nextLine();

            if (!ValidationService.isValidDate(hireDate)) {
                System.out.println("Date must match YYYY-MM-DD format.");
            } else {
                break;
            }
        }

        System.out.print("Status: ");
        String status = scanner.nextLine();

        System.out.print("Role: ");
        String role = scanner.nextLine();

        System.out.print("Clearance Level: ");
        String clearance = scanner.nextLine();

        SimulatedApiPrinter.printCreateWardenRequest(
                id,
                firstName,
                lastName,
                email,
                phone,
                hireDate,
                status,
                role,
                clearance
        );
    }

    private void viewWardens() {

        System.out.println("\n=========================================================");
        System.out.println("VIEW ALL WARDENS");
        System.out.println("=========================================================");

        System.out.printf(
                "%-10s %-15s %-15s %-25s %-15s %-15s %-15s %-15s %-15s%n",
                "ID",
                "FIRST",
                "LAST",
                "EMAIL",
                "PHONE",
                "HIRE DATE",
                "STATUS",
                "ROLE",
                "CLEARANCE"
        );

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        for (Warden w : wardens) {

            System.out.printf(
                    "%-10s %-15s %-15s %-25s %-15s %-15s %-15s %-15s %-15s%n",
                    w.getWardenId(),
                    w.getFirstName(),
                    w.getLastName(),
                    w.getEmail(),
                    w.getPhone(),
                    w.getHireDate(),
                    w.getStatus(),
                    w.getRole(),
                    w.getClearanceLevel()
            );
        }
    }

    private void updateWardenMenu() {

        boolean running = true;

        while (running) {

            System.out.println("\n=========================================================");
            System.out.println("UPDATE WARDEN MENU");
            System.out.println("=========================================================");

            System.out.println("1. Update Status");
            System.out.println("2. Update Role");
            System.out.println("3. Update Contact Information");
            System.out.println("4. Return to Main Menu");

            System.out.print("\nSelect Option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    simulateUpdateStatus();
                    break;

                case "2":
                    simulateUpdateRole();
                    break;

                case "3":
                    simulateUpdateContact();
                    break;

                case "4":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid menu selection.");
            }
        }
    }

    private void manageCertificationsMenu() {

        boolean running = true;

        while (running) {

            System.out.println("\n=========================================================");
            System.out.println("MANAGE CERTIFICATIONS");
            System.out.println("=========================================================");

            System.out.println("1. Add Certification");
            System.out.println("2. Remove Certification");
            System.out.println("3. View Certifications");
            System.out.println("4. Return to Main Menu");

            System.out.print("\nSelect Option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    simulateAddCertification();
                    break;

                case "2":
                    simulateRemoveCertification();
                    break;

                case "3":
                    simulateViewCertifications();
                    break;

                case "4":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid menu selection.");
            }
        }
    }

    private void terminateWarden() {

        System.out.println("\nACTION: Deactivate / Terminate Warden");

        System.out.println("\nInputs Required:");
        System.out.println("- Warden ID");
        System.out.println("- Termination Reason");
        System.out.println("- Effective Date");

        System.out.println("\nValidation:");
        System.out.println("- Warden ID must exist");
        System.out.println("- Date must match YYYY-MM-DD");

        System.out.println("\nWOULD SEND:");
        System.out.println("PUT /api/wardens/{id}/termination");

        System.out.println("\nPAYLOAD (JSON)");

        System.out.println("{");
        System.out.println("  \"status\": \"TERMINATED\"");
        System.out.println("}");

        System.out.println("\nRESULT:");
        System.out.println("SUCCESS (simulated)");
    }

    private void simulateUpdateStatus() {

        System.out.println("\nACTION: Update Warden Status");

        System.out.println("\nInputs Required:");
        System.out.println("- Warden ID");
        System.out.println("- New Status");

        System.out.println("\nValidation:");
        System.out.println("- ID must exist");
        System.out.println("- Status must be valid");

        System.out.println("\nWOULD SEND:");
        System.out.println("PUT /api/wardens/{id}/status");

        System.out.println("\nRESULT:");
        System.out.println("SUCCESS (simulated)");
    }

    private void simulateUpdateRole() {

        System.out.println("\nACTION: Update Warden Role");

        System.out.println("\nInputs Required:");
        System.out.println("- Warden ID");
        System.out.println("- New Role");

        System.out.println("\nValidation:");
        System.out.println("- Role must be approved by system rules");

        System.out.println("\nWOULD SEND:");
        System.out.println("PUT /api/wardens/{id}/role");

        System.out.println("\nRESULT:");
        System.out.println("SUCCESS (simulated)");
    }

    private void simulateUpdateContact() {

        System.out.println("\nACTION: Update Contact Information");

        System.out.println("\nInputs Required:");
        System.out.println("- Warden ID");
        System.out.println("- Updated Email");
        System.out.println("- Updated Phone");

        System.out.println("\nValidation:");
        System.out.println("- Email must remain unique");

        System.out.println("\nWOULD SEND:");
        System.out.println("PUT /api/wardens/{id}/contact");

        System.out.println("\nRESULT:");
        System.out.println("SUCCESS (simulated)");
    }

    private void simulateAddCertification() {

        System.out.println("\nACTION: Add Certification");

        System.out.println("\nInputs Required:");
        System.out.println("- Warden ID");
        System.out.println("- Certification Name");
        System.out.println("- Earned Date");

        System.out.println("\nValidation:");
        System.out.println("- Certification cannot be blank");
        System.out.println("- Date must match YYYY-MM-DD");

        System.out.println("\nWOULD SEND:");
        System.out.println("POST /api/wardens/{id}/certifications");

        System.out.println("\nPAYLOAD (JSON)");

        System.out.println("{");
        System.out.println("  \"name\": \"Rift Safety Level 1\"");
        System.out.println("}");

        System.out.println("\nRESULT:");
        System.out.println("SUCCESS (simulated)");
    }

    private void simulateRemoveCertification() {

        System.out.println("\nACTION: Remove Certification");

        System.out.println("\nInputs Required:");
        System.out.println("- Warden ID");
        System.out.println("- Certification ID");

        System.out.println("\nValidation:");
        System.out.println("- Certification must exist");

        System.out.println("\nWOULD SEND:");
        System.out.println("DELETE /api/wardens/{id}/certifications/{certId}");

        System.out.println("\nRESULT:");
        System.out.println("SUCCESS (simulated)");
    }

    private void simulateViewCertifications() {

        System.out.println("\nACTION: View Certifications");

        System.out.println("\nInputs Required:");
        System.out.println("- Warden ID");

        System.out.println("\nWOULD SEND:");
        System.out.println("GET /api/wardens/{id}/certifications");

        System.out.println("\nRESULT:");
        System.out.println("Certification records returned successfully (simulated)");
    }
}
