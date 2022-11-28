package Database;
import Database.Database;
import FileHandler.FileHandler;
import MemberClass.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private FileHandler fileHandler;
    private Database database;

    public Controller() {
        fileHandler = new FileHandler();
        database = new Database();
    }

    public void addMember(String name, LocalDate dateOfBirth, String email , String address, int phoneNumber, boolean activityType) {
        database.addMember(name, dateOfBirth, email, address, phoneNumber, activityType);
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

    public boolean removeMember(int nr) {
        return database.removeMember(nr);
    }
}
