public class TablePrinter {

    public static void printCreatureHeader() {

        System.out.println(
                "+----+----------------+----------------+-----------+");

        System.out.printf(
                "| %-2s | %-14s | %-14s | %-9s |\n",
                "ID",
                "Name",
                "Habitat",
                "Status"
        );

        System.out.println(
                "+----+----------------+----------------+-----------+");
    }

    public static void printCreatureRow(
            String id,
            String name,
            String habitat,
            String status) {

        System.out.printf(
                "| %-2s | %-14s | %-14s | %-9s |\n",
                id,
                name,
                habitat,
                status
        );
    }

    public static void printFooter() {

        System.out.println(
                "+----+----------------+----------------+-----------+");
    }
}