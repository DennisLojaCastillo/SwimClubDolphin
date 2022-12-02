package Database;
import FileHandler.FileHandler;
import MemberClass.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private final FileHandler fileHandler;
    private final Database database;

    public Controller() {
        fileHandler = new FileHandler();
        database = new Database();
    }

    public void addMember(String name, LocalDate dateOfBirth, String email , String address, int phoneNumber, boolean memberType, boolean activityType, boolean hasPaid) {
        database.addMember(name, dateOfBirth, email, address, phoneNumber,memberType, activityType, hasPaid);
    }

    public ArrayList<Member> getMembers() {
        return database.getMembers();
    }
    public void loadMembers() {
        database.addArrayMember(fileHandler.loadMembers());
    }

    public void saveMembers() {
        fileHandler.saveMembers(database.getMembers());
    }

    public void removeMember(int nr) {
        database.removeMember(nr);
    }
}
