package Database;
import MemberClass.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database {

    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Competition> records = new ArrayList<>();

    public void addArrayMember(ArrayList<Member> m) {
        members = m;
    }
    public void addArrayRecords(ArrayList<Competition> c) {
        records = c;
    }

    public void addRecord(String memberID, String name, String eventName, int placement, double bestTime) {
        records.add(new Competition(memberID, name, eventName, placement, bestTime));
    }

    public void addMember(String name, LocalDate dateOfBirth, String email, String address, int phoneNumber, boolean memberType, boolean activityType, boolean hasPaid) {
        members.add(new Member(name, dateOfBirth, email, address, phoneNumber, memberType, activityType, hasPaid));
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<Member> getMembersComp() {
        ArrayList<Member> compMembers = new ArrayList<>();
        for (Member member : members) {
            if (member.isActivityType()) {
                compMembers.add(member);
            }
        }
        return compMembers;
    }
    public ArrayList<Competition> getRecords() {
        return records;
    }

    public ArrayList<Member> getMembersRestance() {
        ArrayList<Member> restanceMembers = new ArrayList<>();
        for (Member member : members) {
            if (!member.getHasPaid()) {
                restanceMembers.add(member);
            }
        }
        return restanceMembers;
    }
    public ArrayList<Member> getProfitOfMembers() {
        ArrayList<Member> profitMembers = new ArrayList<>();
        for (Member member : members) {
            if (member.getHasPaid()) {
                profitMembers.add(member);
            }
        }
        return profitMembers;
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

