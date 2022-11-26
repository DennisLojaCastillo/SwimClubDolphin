import java.time.LocalDate;

public class Competitor extends Member{
    public Competitor(String name, LocalDate dateOfBirth, String email, String address, int phoneNumber, boolean activityType, String membership) {
        super(name, dateOfBirth, email, address, phoneNumber, activityType, membership);
    }
}
