import java.time.LocalDate;

// TODO Future sprint!!!
public class Competitor extends Member{
    public Competitor(String name, LocalDate dateOfBirth, String email, String address, int phoneNumber, boolean activityType) {
        super(name, dateOfBirth, email, address, phoneNumber, activityType);
        format();
        ageCalculator();
        setMembershipType("competitor");
    }
}
