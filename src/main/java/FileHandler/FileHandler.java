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

    public ArrayList<Member> loadMembers() {
        ArrayList<Member> memberList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("src/main/resources/medlemmer.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] att = line.split(",");
                Member memberLoad = new
                        Member(att[0],
                        LocalDate.parse(att[1]),
                        att[2],
                        att[3],
                        Integer.parseInt(att[4]),
                        Boolean.parseBoolean(att[5]),
                        Boolean.parseBoolean(att[6]),
                        Boolean.parseBoolean(att[7]));

                memberList.add(memberLoad);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read the file...");
        }
        return memberList;
    }
}