import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    public void saveMembers(ArrayList<Member> m) {
        try {
            PrintStream saveToFile = new PrintStream("src/medlemmer.txt");
            for (Member member : m) {
                saveToFile.println(member.getName() + "," + member.getEmail() + "," + member.getSocialSecurityNumber() + "," + member.getAddress() + "," + member.getPhoneNumber() + "," + member.getActivityType() + "," + member.getMembership());
            }
        } catch(IOException e) {
            System.out.println("Fejl!");
        }
    }

    public ArrayList<Member> loadMembers() {
        ArrayList<Member> memberList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("src/medlemmer.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] att = line.split(",");
                Member memberLoad = new Member(
                        att[0],
                        att[1],
                        Integer.parseInt(att[2]),
                        att[3],
                        Integer.parseInt(att[4]),
                        Boolean.parseBoolean(att[5]),
                        att[6]
                        );
                memberList.add(memberLoad);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fejl i læsning af fil");
        }
        return memberList;
    }
}