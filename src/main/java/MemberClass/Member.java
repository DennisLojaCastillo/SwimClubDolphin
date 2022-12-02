package MemberClass;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Member {
    private String memberID;
    private String name;
    private int age;
    private LocalDate dateOfBirth;
    private String email;
    private String address;
    private int phoneNumber;
    private boolean memberType;
    private final boolean activityType;
    private String membershipAgeType;
    private int membershipAnnualPayment;
    private String formatted;
    private boolean hasPaid;

    public Member(String name, LocalDate dateOfBirth, String email, String address, int phoneNumber, boolean memberType, boolean activityType, boolean hasPaid) {
        this.memberID = createID();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.memberType = memberType;
        this.activityType = activityType;
        this.hasPaid = hasPaid;
        format();
        ageCalculator();
        membershipAgeType();
        membershipFee();
    }

    public Member(String memberID, String name, LocalDate dateOfBirth, String email, String address, int phoneNumber, boolean memberType, boolean activityType, boolean hasPaid) {
        this.memberID = memberID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.memberType = memberType;
        this.activityType = activityType;
        this.hasPaid = hasPaid;
        format();
        ageCalculator();
        membershipAgeType();
        membershipFee();
    }

    private String createID(){
        var full = UUID.randomUUID();
        var fullAsString = full.toString();
        var firstFourCharacters = fullAsString.substring(0,4);
        return firstFourCharacters;
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
            this.membershipAgeType = "Junior";
        } else if (age > 60) {
            this.membershipAgeType = "Senior";
        } else {
            this.membershipAgeType = "Adult";
        }
    }

    public void membershipFee() {

        if (!memberType) {
            membershipAnnualPayment = 500;
        } else if (membershipAgeType.equals("Junior")) {
            membershipAnnualPayment = 1000;
        } else if (membershipAgeType.equals("Adult")) {
            membershipAnnualPayment = 1600;
        } else if (membershipAgeType.equals("Senior")) {
            membershipAnnualPayment = 1200;
        }
    }

    public String getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public int getMembershipAnnualPayment(){
        return membershipAnnualPayment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        format();
        ageCalculator();
        membershipAgeType();
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

    public boolean getMemberType() {
        return memberType;
    }

    public void setMemberType(boolean memberType) {
        this.memberType = memberType;
    }

    public boolean isActivityType() {
        return activityType;
    }

    public boolean getHasPaid() {
        return hasPaid;
    }
    public void setHasPaid(boolean hasPaid){
        this.hasPaid = hasPaid;
    }

    @Override
    public String toString() {
        return  "Member ID: " + memberID
                + "\nName: " + name
                + "\nDate of birth: " + formatted
                + "\nAge: " + age
                + "\nEmail: " + email
                + "\nAddress: " + address
                + "\nPhone Number: " + phoneNumber
                + "\nMember Type: " + (memberType ? "Active" : "Passive")
                + "\nActivity Type: " + (activityType ? "Competitor" : "Motionist")
                + "\nMembership: " + membershipAgeType
                + "\nAnnual Payment Fee: " + membershipAnnualPayment + " kr\n";
    }
}