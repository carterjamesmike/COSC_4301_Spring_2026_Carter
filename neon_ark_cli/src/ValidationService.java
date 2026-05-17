import java.time.LocalDate;
import java.util.List;

public class ValidationService {

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidDate(String date) {

        try {
            LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDuplicateId(List<Warden> wardens, String id) {

        for (Warden w : wardens) {
            if (w.getWardenId().equalsIgnoreCase(id)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isDuplicateEmail(List<Warden> wardens, String email) {

        for (Warden w : wardens) {
            if (w.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }
}