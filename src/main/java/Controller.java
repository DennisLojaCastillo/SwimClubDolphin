import java.lang.reflect.Array;
import java.util.ArrayList;

public class Controller {
    private FileHandler fileHandler;
    private Database database;

    public Controller() {
        fileHandler = new FileHandler();
        database = new Database();
    }

    public void addMember(String name, String email, int socialSecurityNumber , String address, int phoneNumber, boolean activityType, String membership) {
        database.addMember(name, email, socialSecurityNumber , address, phoneNumber, activityType, membership);
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
