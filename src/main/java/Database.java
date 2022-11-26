import java.time.LocalDate;
import java.util.ArrayList;

public class Database {

    private ArrayList<Member> members = new ArrayList<>();

    public void addArrayMember(ArrayList<Member> m) {
        members = m;
    }

    public void addMember(String name, LocalDate dateOfBirth, String email, String address, int phoneNumber, boolean activityType, String membership) {
        members.add(new Member(name, dateOfBirth, email, address, phoneNumber, activityType, membership));
    }

    public ArrayList<Member> getMembers() {
        return members;
    }


    public boolean removeMember(int nr) {
        if (nr <= members.size()) {
            members.remove(nr - 1);
            return true;
        } else {
            return false;
        }
    }
}

