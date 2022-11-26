import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private FileHandler fileHandler;
    private Database database;

    public Controller() {
        fileHandler = new FileHandler();
        database = new Database();
    }

    public void addMember(String name, LocalDate dateOfBirth, String email , String address, int phoneNumber, boolean activityType, String membership) {
        database.addMember(name, dateOfBirth, email, address, phoneNumber, activityType, membership);
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
