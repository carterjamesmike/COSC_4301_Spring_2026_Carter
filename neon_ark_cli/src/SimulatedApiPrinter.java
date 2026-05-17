public class SimulatedApiPrinter {

    public static void printCreateWardenRequest(
            String id,
            String firstName,
            String lastName,
            String email,
            String phone,
            String hireDate,
            String status,
            String role,
            String clearance
    ) {

        System.out.println("\n=========================================================");
        System.out.println("SIMULATED OUTBOUND REQUEST");
        System.out.println("=========================================================");

        System.out.println("WOULD SEND: POST /api/wardens");

        System.out.println("\nBRIEF DESCRIPTION:");
        System.out.println("Create a new Warden onboarding record.");

        System.out.println("\nPAYLOAD (JSON)");

        System.out.println("{");
        System.out.println("  \"wardenId\": \"" + id + "\",");
        System.out.println("  \"firstName\": \"" + firstName + "\",");
        System.out.println("  \"lastName\": \"" + lastName + "\",");
        System.out.println("  \"email\": \"" + email + "\",");
        System.out.println("  \"phone\": \"" + phone + "\",");
        System.out.println("  \"hireDate\": \"" + hireDate + "\",");
        System.out.println("  \"status\": \"" + status + "\",");
        System.out.println("  \"role\": \"" + role + "\",");
        System.out.println("  \"clearanceLevel\": \"" + clearance + "\"");
        System.out.println("}");

        System.out.println("\nRESULT:");
        System.out.println("SUCCESS (simulated)");
    }
}