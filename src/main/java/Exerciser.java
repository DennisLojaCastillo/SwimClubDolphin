import java.time.LocalDate;

public class Exerciser extends Member{
    public Exerciser(String name, String email, LocalDate dateOfBirth, String address, int phoneNumber, boolean activityType, String membership) {
        super(name, dateOfBirth, email, address, phoneNumber, activityType, membership);
    }
}
