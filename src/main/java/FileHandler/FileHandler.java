package FileHandler;

import MemberClass.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    public void saveMembers(ArrayList<Member> m) {
        try {
            PrintStream saveToFile = new PrintStream("src/main/resources/medlemmer.txt");
            for (Member member : m) {
                saveToFile.println(
                            member.getMemberID() + "," +
                            member.getName() + "," +
                            member.getDateOfBirth() + "," +
                            member.getEmail() + "," +
                            member.getAddress() + "," +
                            member.getPhoneNumber() + "," +
                            member.getMemberType() + "," +
                            member.isActivityType() + "," +
                            member.getHasPaid());
            }
        } catch(IOException e) {
            System.out.println("Fejl!");
        }
    }

    public void saveRecords(ArrayList<Competition> c) {
        try {
            PrintStream saveToFile = new PrintStream("src/main/resources/records.txt");
            for (Competition competition : c) {
                saveToFile.println(
                        competition.getMemberID() + "," +
                                competition.getName() + "," +
                                competition.getEventName() + "," +
                                competition.getPlacement() + "," +
                                competition.getBestTime());
            }
        } catch(IOException e) {
            System.out.println("Fejl!");
        }
    }

    public ArrayList<Member> loadMembers() {
        ArrayList<Member> memberList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("src/main/resources/medlemmer.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] att = line.split(",");
                Member memberLoad = new Member(att[0],
                        att[1],
                        LocalDate.parse(att[2]),
                        att[3],
                        att[4],
                        Integer.parseInt(att[5]),
                        Boolean.parseBoolean(att[6]),
                        Boolean.parseBoolean(att[7]),
                        Boolean.parseBoolean(att[8]));
                memberList.add(memberLoad);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read the file...");
        }
        return memberList;
    }

    public ArrayList<Competition> loadRecords() {
        ArrayList<Competition> compList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("src/main/resources/records.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] att = line.split(",");
                Competition compLoad = new Competition(att[0],
                        att[1],
                        att[2],
                        Integer.parseInt(att[3]),
                        Integer.parseInt(att[4]));
                compList.add(compLoad);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read the file...");
        }
        return compList;
    }
}