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
    private String membershipType;
    private String membershipAgeType;
    private String formatted;

    public Member(String name, LocalDate dateOfBirth, String email, String address, int phoneNumber, boolean activityType) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.activityType = activityType;
        format();
        ageCalculator();
        membershipAgeType();
    }

    public void format() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        formatted = dateOfBirth.format(format);
    }

    public void ageCalculator() {
        LocalDate today = LocalDate.now();
        this.age = Period.between(dateOfBirth, today).getYears();
    }

    public void membershipAgeType() {
        ageCalculator();
        if (age < 18) {
            this.membershipAgeType = "junior";
        } else if (age > 60) {
            this.membershipAgeType = "Senior";
        } else {
            this.membershipAgeType = "Adult";
        }

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

    public String getMembershipType(){
        return membershipType;
    }

    public void setMembershipType(String membershipType){
        this.membershipType = membershipType;
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
                + "\nMembership: " + membershipAgeType  + "\n";
    }
}
