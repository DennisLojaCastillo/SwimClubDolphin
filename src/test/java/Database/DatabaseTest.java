package Database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
                true);
        database.removeMember(1);
        assertEquals(0, database.getMembers().size());
    }

    @org.junit.jupiter.api.Test
    void removeMember2() {
        database.addMember(" Amir", LocalDate.parse("01-01-2001", formatDate), "Amir2700@hotmail.com", "chupapi 21", 9999,
                true);
        database.removeMember(2);
        assertEquals(1, database.getMembers().size());
    }

    @org.junit.jupiter.api.Test
    void removeMember3() {
        database.removeMember(2);
        assertEquals(0, database.getMembers().size());
    }
}