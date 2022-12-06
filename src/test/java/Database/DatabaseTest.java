package Database;

import MemberClass.Member;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Database database = new Database();
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @org.junit.jupiter.api.Test
    void addArrayMember() {
    }

    @org.junit.jupiter.api.Test
    void addMember() {
    }

    @org.junit.jupiter.api.Test
    void getMembers() {
    }

    @org.junit.jupiter.api.Test
    void removeMember() {
        database.addMember(" Amir", LocalDate.parse("01-01-2001", formatDate), "Amir2700@hotmail.com", "chupapi 21", 9999,
                true, true, false);
        database.removeMember(1);
        assertEquals(0, database.getMembers().size());
    }

    @org.junit.jupiter.api.Test
    void removeMember2() {
        database.addMember(" Amir", LocalDate.parse("01-01-2001", formatDate), "Amir2700@hotmail.com", "chupapi 21", 9999,
                true, true, false);
        database.removeMember(2);
        assertEquals(1, database.getMembers().size());
    }

    @org.junit.jupiter.api.Test
    void removeMember3() {
        database.removeMember(1);
        assertEquals(0, database.getMembers().size());
    }

    @Test
    void restanceListe() {
        //Arrange testdata
        database.addMember("nichlas", LocalDate.now(), null, null, 0, true, true, true);
        database.addMember("Tine", LocalDate.now(), null, null, 0, true, true, false);
        //Act til Member i restance metode
        ArrayList<Member> restanceMedlemmer = database.getMembersRestance();
        //Assert at metode gør hvad den skal
        assertEquals(1, restanceMedlemmer.size());
    }

    @Test
    void kontigentBeregningJunior() {
        Member juninor = new Member("nichlas", LocalDate.of(2012, 10, 10), null, null, 0, true, true, true);
        int exspectedFee = 1000;
        int fee = juninor.getMembershipAnnualPayment();
        assertEquals(exspectedFee, fee);
    }

    @Test
    void kontigentBeregningAdult() {
        Member adult = new Member("Amir", LocalDate.of(1998, 10, 10), null, null, 0, true, true, true);
        int exspectedFee = 1600;
        int fee = adult.getMembershipAnnualPayment();
        assertEquals(exspectedFee, fee);
    }

    @Test
    void kontigentBeregningSenior() {
        Member senior = new Member("Dennis", LocalDate.of(1950, 10, 10), null, null, 0, true, true, true);
        int exspectedFee = 1200;
        int fee = senior.getMembershipAnnualPayment();
        assertEquals(exspectedFee, fee);
    }

    @Test
    void kontigentBeregningPassive() {
        Member passive = new Member("Samim", LocalDate.of(2012, 10, 10), null, null, 0, false, true, true);
        int exspectedFee = 500;
        int fee = passive.getMembershipAnnualPayment();
        assertEquals(exspectedFee, fee);
    }
}
