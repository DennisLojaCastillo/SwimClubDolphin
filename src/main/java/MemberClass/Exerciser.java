package MemberClass;

import MemberClass.*;

import java.time.LocalDate;
// TODO Future sprint!!!
public class Exerciser extends Member {
    public Exerciser(String name, String email, LocalDate dateOfBirth, String address, int phoneNumber, boolean activityType) {
        super(name, dateOfBirth, email, address, phoneNumber, activityType);
        format();
        ageCalculator();
        setMembershipType("exerciser");
    }
}
