import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Member {
    private String name;
    private int age;
    private LocalDate dateOfBirth;
    private String email;
    private String address;
    private int phoneNumber;
    private boolean activityType;
    private String membership;
    private String formatted;

    public Member(String name, LocalDate dateOfBirth, String email, String address, int phoneNumber, boolean activityType, String membership) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.activityType = activityType;
        this.membership = membership;
        format();
        ageCalculator();
    }

    public void format() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        formatted = dateOfBirth.format(format);
    }

    public void ageCalculator() {
        LocalDate today = LocalDate.now();
        this.age = Period.between(dateOfBirth, today).getYears();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getActivityType() {
        return activityType;
    }

    public void setActivityType(boolean activityType) {
        this.activityType = activityType;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    @Override
    public String toString() {
        return "Name: " + name
                + "\nDate of birth: " + formatted
                + "\nAge: " + age
                + "\nEmail: " + email
                + "\nAddress: " + address
                + "\nPhone Number: " + phoneNumber
                + "\nActivity Type: " + (activityType ? "Aktiv" : "Passiv")
                + "\nMembership: " + membership + "\n";
    }
}
