public class Warden {

    private String wardenId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String hireDate;
    private String status;
    private String role;
    private String clearanceLevel;

    public Warden(String wardenId, String firstName, String lastName,
                  String email, String phone, String hireDate,
                  String status, String role, String clearanceLevel) {

        this.wardenId = wardenId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.status = status;
        this.role = role;
        this.clearanceLevel = clearanceLevel;
    }

    public String getWardenId() {
        return wardenId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }

    public String getClearanceLevel() {
        return clearanceLevel;
    }
}