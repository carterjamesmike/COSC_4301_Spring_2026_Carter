import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api/creatures";

public static void listCreatures() {

    try {

        URL url = new URL(BASE_URL);

        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            response.append(line);
        }

        String json = response.toString();

        json = json.replace("[", "")
                .replace("]", "")
                .replace("{", "")
                .replace("}", "");

        String[] creatures = json.split("id");

        TablePrinter.printCreatureHeader();

        for (String creature : creatures) {

            if (creature.isBlank()) {
                continue;
            }

            String[] fields = creature.split(",");

            String id = "";
            String name = "";
            String habitat = "";
            String status = "";

            for (String field : fields) {

                if (field.contains(":")) {

                    String[] pair = field.split(":");

                    if (pair.length < 2) {
                        continue;
                    }

                    String key =
                            pair[0].replace("\"", "")
                                    .replace(":", "")
                                    .trim();

                    String value =
                            pair[1].replace("\"", "")
                                    .trim();

                    switch (key) {

                        case "" -> id = value;

                        case "name" -> name = value;

                        case "habitatName" -> habitat = value;

                        case "status" -> status = value;
                    }
                }
            }

            if (!name.isBlank()) {

                TablePrinter.printCreatureRow(
                        id,
                        name,
                        habitat,
                        status
                );
            }
        }

        TablePrinter.printFooter();

    } catch (Exception e) {

        System.out.println(e.getMessage());
    }
}

public static void viewCreature(Scanner scanner) {

    try {

        System.out.print("Enter creature ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        URL url = new URL(BASE_URL + "/" + id);

        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        int statusCode = conn.getResponseCode();

        if (statusCode == 404) {

            System.out.println(
                    "ERROR: Creature not found."
            );

            return;
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        StringBuilder response = new StringBuilder();

        String line;

        while ((line = br.readLine()) != null) {
            response.append(line);
        }

        String json = response.toString();

        json = json.replace("{", "")
                .replace("}", "");

        String[] fields = json.split(",");

        String creatureId = "";
        String name = "";
        String habitat = "";
        String status = "";

        for (String field : fields) {

            String[] pair = field.split(":");

            if (pair.length < 2) {
                continue;
            }

            String key =
                    pair[0].replace("\"", "").trim();

            String value =
                    pair[1].replace("\"", "").trim();

            switch (key) {

                case "id" -> creatureId = value;

                case "name" -> name = value;

                case "habitatName" -> habitat = value;

                case "status" -> status = value;
            }
        }

        TablePrinter.printCreatureHeader();

        TablePrinter.printCreatureRow(
                creatureId,
                name,
                habitat,
                status
        );

        TablePrinter.printFooter();

    } catch (Exception e) {

        System.out.println(
                "ERROR: Unable to retrieve creature."
        );
    }
}

public static void createCreature(Scanner scanner) {

    try {

        System.out.print("Enter creature name: ");
        String name = scanner.nextLine();

        if (name.isBlank()) {

            System.out.println(
                    "ERROR: Name cannot be blank."
            );

            return;
        }

        System.out.print("Enter habitat ID: ");
        int habitatId = scanner.nextInt();
        scanner.nextLine();

        URL url = new URL(BASE_URL);

        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");

        conn.setRequestProperty(
                "Content-Type",
                "application/json"
        );

        conn.setDoOutput(true);

        String jsonInput = String.format(
                "{\"name\":\"%s\",\"habitatId\":\"%d\"}",
                name,
                habitatId
        );

        try (OutputStream os = conn.getOutputStream()) {

            byte[] input =
                    jsonInput.getBytes("utf-8");

            os.write(input, 0, input.length);
        }

        int statusCode = conn.getResponseCode();

        if (statusCode == 400) {

            System.out.println(
                    "ERROR: Invalid creature data."
            );

            return;
        }

        if (statusCode == 409) {

            System.out.println(
                    "ERROR: Creature already exists."
            );

            return;
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        conn.getInputStream(),
                        "utf-8"
                )
        );

        StringBuilder response =
                new StringBuilder();

        String responseLine;

        while ((responseLine = br.readLine()) != null) {

            response.append(
                    responseLine.trim()
            );
        }

        System.out.println(
                "SUCCESS: Creature created."
        );

        System.out.println(response);

    } catch (Exception e) {

        System.out.println(
                "ERROR: Unable to create creature."
        );
    }
}

public static void renameCreature(Scanner scanner) {

    try {

        System.out.print("Enter creature ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();

        if (newName.isBlank()) {

            System.out.println(
                    "ERROR: Name cannot be blank."
            );

            return;
        }

        System.out.print(
                "Confirm rename? (y/n): "
        );

        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("y")) {

            System.out.println(
                    "WARNING: Rename cancelled."
            );

            return;
        }

        URL url = new URL(
                BASE_URL + "/" + id + "/name"
        );

        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("PUT");

        conn.setRequestProperty(
                "Content-Type",
                "application/json"
        );

        conn.setDoOutput(true);

        String jsonInput =
                String.format(
                        "{\"newName\":\"%s\"}",
                        newName
                );

        try (OutputStream os = conn.getOutputStream()) {

            byte[] input =
                    jsonInput.getBytes("utf-8");

            os.write(input, 0, input.length);
        }

        int statusCode = conn.getResponseCode();

        if (statusCode == 404) {

            System.out.println(
                    "ERROR: Creature not found."
            );

            return;
        }

        if (statusCode == 409) {

            System.out.println(
                    "ERROR: Duplicate creature name."
            );

            return;
        }

        if (statusCode == 400) {

            System.out.println(
                    "ERROR: Invalid creature name."
            );

            return;
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        conn.getInputStream()
                )
        );

        String line;

        while ((line = br.readLine()) != null) {

            System.out.println(line);
        }

        System.out.println(
                "SUCCESS: Creature renamed."
        );

    } catch (Exception e) {

        System.out.println(
                "ERROR: Unable to rename creature."
        );
    }
}

public static void removeCreature(Scanner scanner) {

    try {

        System.out.print("Enter creature ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Confirm removal? (y/n): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("y")) {

            System.out.println(
                    "WARNING: Removal cancelled."
            );

            return;
        }

        URL url = new URL(BASE_URL + "/" + id);

        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("DELETE");

        int statusCode = conn.getResponseCode();

        if (statusCode == 404) {

            System.out.println(
                    "ERROR: Creature not found."
            );

            return;
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        System.out.println(
                "SUCCESS: Creature removed."
        );

    } catch (Exception e) {

        System.out.println(
                "ERROR: Unable to remove creature."
        );
    }
}

public static void viewObservations(Scanner scanner) {

    try {

        System.out.print("Enter creature ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        URL url = new URL(
                BASE_URL + "/" + id + "/observations"
        );

        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

    } catch (Exception e) {
        System.out.println(e.getMessage());
        }
    }

    public static void findFeedings(Scanner scanner) {

    try {

        System.out.print("Enter feeding time (HH:MM): ");
        String time = scanner.nextLine();

        URL url = new URL(
                "http://localhost:8080/api/feedings?time=" + time
        );

        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

    } catch (Exception e) {
        System.out.println(e.getMessage());
        }
    }

    public static void viewUsers() {

    try {

        URL url = new URL(
                "http://localhost:8080/api/admin/users"
        );

        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
}