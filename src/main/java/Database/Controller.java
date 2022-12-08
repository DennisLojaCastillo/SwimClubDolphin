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

    public void addRecord(String memberID, String name, String eventName, int placement, double bestTime, String disciplin) {
        database.addRecord(memberID, name, eventName, placement, bestTime, disciplin);
    }

    public ArrayList<Competition> getRecords() {
        return database.getRecords();
    }

    public ArrayList<Member> getMembers() {
        return database.getMembers();
    }
    public ArrayList<Member> getMembersComp() {
        return database.getMembersComp();
    }


    public ArrayList<Member> getMembersCompSpecific(String group) {
        ArrayList<Member> tempMembers = new ArrayList<>();
        for(Member member : getMembersComp()) {
            if(member.membershipType() == group) {
                tempMembers.add(member);
            }
        }
        return tempMembers;
    }

    //Listing disciplin records
    public ArrayList<Competition> getMemberDisciplin(String group) {
        ArrayList<Competition> tempMembers = new ArrayList<>();
        for(Competition competition : getRecords()) {
            if(competition.getDisciplin().equals(group)) {
                tempMembers.add(competition);
            } else if(group.equals("all")) {
                tempMembers.add(competition);
            }
        }
        return tempMembers;
    }

    public void loadMembers() {

        database.addArrayMember(fileHandler.loadMembers());
        database.addArrayRecords(fileHandler.loadRecords());
    }

    public void saveMembers() {
        fileHandler.saveMembers(database.getMembers());
        fileHandler.saveRecords(database.getRecords());
    }

    public ArrayList<Member> getMembersRestance() {
        return database.getMembersRestance();
    }

    public int getMissingProfit(){
        int profitMissing = 0;
        for (Member member : getMembersRestance()) {
            profitMissing += member.getMembershipAnnualPayment();
        }
        return profitMissing;
    }
    public ArrayList<Member> getMemberProfit() {
        return database.getProfitOfMembers();
    }
    public int getTotalProfit() {
        int totalProfit = 0;
        for (Member member : getMemberProfit()) {
            totalProfit += member.getMembershipAnnualPayment();
        }
        return totalProfit;
    }

    public void removeMember(int nr) {
        database.removeMember(nr);
    }

    public Trainer getTrainer(String ageGroup) {
        for(Trainer trainer : database.getTrainers()) {
            if(trainer.getTrainerGroup() == ageGroup) {
                return trainer;
            }
        }
        return null;
    }

}
