package UI;

import Comparators.PlacementComparator;
import Comparators.TimeComparator;
import Database.Controller;
import MemberClass.*;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface {
    private final Scanner scanner;
    private final Controller controller;
    LocalDate validDate = null;
    private Trainer assignedTrainer;

    public UserInterface() {
        scanner = new Scanner(System.in);
        controller = new Controller();
        controller.loadMembers();
    }

    public void startProgram() {
        int menuChoice;
        do {
            System.out.println("""
                    Dolphin Swim Club Administration
                    ------------------------------------
                    1. Chairman Management
                    2. Cashier Management
                    3. Coach Management
                                        
                    9. Close Programme
                    """);
            menuChoice = readInteger();
            scanner.nextLine();
            handlingMenuChoice(menuChoice);
        } while (menuChoice != 9);
    }

    public void handlingMenuChoice(int menuChoice) {
        switch (menuChoice) {
            case 1 -> chairmanMenu();
            case 2 -> cashierMenu();
            case 3 -> coachSelectMenu();
            case 9 -> {
                System.out.println("Closing programme...");
                controller.saveMembers();
            }
            default -> System.out.println("""   
                    Could not handle input. Please try again
                    Choose menu item from 1-3
                    """);
        }

    }


    //----------------------------------------------------------------------------------------------------------------
    // Chairman Menu
    public void chairmanMenu() {
        int userChoice;
        do {
            System.out.println("""
                    Logged in as Chairman
                     ------------------------------------
                    1. Add Member
                    2. See Members List
                    3. Edit Member
                    4. Remove Member
                                        
                    9. Go back
                     """);
            userChoice = readInteger();
            scanner.nextLine();
            handlingChairmanChoice(userChoice);
        } while (userChoice != 9);
    }

    public void handlingChairmanChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> addMember();
            case 2 -> listMembers();
            case 3 -> editMember();
            case 4 -> deleteMember();
            case 9 -> System.out.println("Going back\n");
            default -> System.out.println("""   
                    Could not handle input. Please try again
                    Choose menu item from 1-4
                    """);
        }
    }

    public void addMember() {
        System.out.println("Enter name: ");
        String name = readString();

        System.out.println("Enter date of birth [Example: 01-01-2001]: ");
        localeDateFailMsg(readString());
        LocalDate dateOfBirth = validDate;

        System.out.println("Enter e-mail: ");
        String email = readString();

        System.out.println("Enter address: ");
        String address = readString();

        System.out.println("Enter phone number: ");
        int phoneNumber = readInteger();
        scanner.nextLine();

        boolean legalMembershipType = false;
        boolean memberType = false;
        while (!legalMembershipType) {
            System.out.println("""
                    Select Member Type:
                    1. Active
                    2. Passive
                    """);
            int memType = readInteger();
            switch (memType) {
                case 1 -> {
                    memberType = true;
                    legalMembershipType = true;
                }
                case 2 -> legalMembershipType = true;

                default -> System.out.println("Member Type not found! Try again.");
            }
        }

        boolean legalActivity = false;
        boolean activityType = false;

        while (!legalActivity) {
            System.out.println("""
                    Select Activity:
                    1. Competitor
                    2. Motionist
                    """);
            int actType = readInteger();
            switch (actType) {
                case 1 -> {
                    activityType = true;
                    legalActivity = true;
                }
                case 2 -> legalActivity = true;

                default -> System.out.println("Activity not found! Try again.");
            }
        }

        controller.addMember(name, dateOfBirth, email, address, phoneNumber, memberType, activityType, false);

        System.out.println("Member registered!");

    }

    public void listMembers() {
        if (controller.getMembers().size() < 1) {
            printNoMemberFoundMsg();
        } else {
            AsciiTable at = new AsciiTable();
            int totalProfit = 0;
            int nr = 1;
            at.addRule();
            at.addRow("No.", "ID", "Name", "Birthdate", "Email", "Address", "Phone Number", "Member Type", "Activity Type", "Membership", "Annual Payment").setTextAlignment(TextAlignment.CENTER);
            for (Member member : controller.getMembers()) {
                at.addRule();
                DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                at.addRow(nr, member.getMemberID(), member.getName(), (member.getDateOfBirth().format(formatDate)), member.getEmail(), member.getAddress(), member.getPhoneNumber(), (member.getMemberType() ? "Active" : "Passive"), (member.isActivityType() ? "Competitor" : "Motionist"), member.membershipType(), (member.getMembershipAnnualPayment() + "kr")).setTextAlignment(TextAlignment.CENTER);
                if (member.getHasPaid()) totalProfit += member.getMembershipAnnualPayment();
                nr++;
            }
            at.addRule();
            at.addRow(null, null, null, null, null, null, null, null, null, "Total profit:", (totalProfit + "kr")).setTextAlignment(TextAlignment.CENTER);
            at.addRule();
            at.getRenderer().setCWC(new CWC_FixedWidth().add(5).add(10).add(30).add(15).add(30).add(35).add(15).add(15).add(15).add(15).add(18));
            String rend = at.render();
            System.out.println(rend + "\n");
        }
    }


    public void editMember() {
        ArrayList<Member> memberList = controller.getMembers();
        if (memberList.size() < 1) {
            printNoMemberFoundMsg();
        } else {
            listMembers();
            System.out.println("Enter a number to edit the members information:");
            int num = readInteger();
            Member editMember;
            scanner.nextLine();
            if (num - 1 >= controller.getMembers().size()) {
                System.out.println("Member ID not found!");
            } else {
                editMember = memberList.get(num - 1);
                System.out.println("You are editing " + editMember.getName());

                System.out.println("Current Name: " + editMember.getName());
                System.out.println("Please enter the new NAME below");
                String newName = readString();
                if (!newName.isEmpty()) {
                    editMember.setName(newName);
                }

                System.out.println("Current date of birth: " + editMember.getDateOfBirth());
                System.out.println("Please enter the new date of birth below [Example: 01-01-2001]");
                String newDate = readString();
                if (!newDate.isEmpty()) {
                    localeDateFailMsg(newDate);
                    editMember.setDateOfBirth(validDate);
                }

                System.out.println("Current Email: " + editMember.getEmail());
                System.out.println("Please enter the new EMAIL name below");
                String newEmail = readString();
                if (!newEmail.isEmpty()) {
                    editMember.setEmail(newEmail);
                }

                System.out.println("Current address " + editMember.getAddress());
                System.out.println("Please enter the new address below");
                String newAddress = readString();
                if (!newAddress.isEmpty()) {
                    editMember.setAddress(newAddress);
                }

                System.out.println("Current Phone Number: " + editMember.getPhoneNumber());
                System.out.println("Please enter the new phone number below");
                String newPhoneNumber = readString();
                if (!newPhoneNumber.isEmpty()) {
                    editMember.setPhoneNumber(Integer.parseInt(newPhoneNumber));
                }

                System.out.println("Current Activity Type: " + editMember.getMemberType());
                System.out.println("Please enter the new activity below");
                boolean legalActivity = false;
                while (!legalActivity) {
                    System.out.println("""
                            Select activity:
                            1. Active
                            2. Passive
                            """);
                    int actType = readInteger();
                    switch (actType) {
                        case 1 -> {
                            editMember.setMemberType(true);
                            legalActivity = true;
                        }
                        case 2 -> {
                            editMember.setMemberType(false);
                            legalActivity = true;
                        }
                        default -> System.out.println("Activity not found! Try again.");
                    }
                }
            }
        }
    }

    public void deleteMember() {
        if (controller.getMembers().size() == 0) {
            System.out.println("\nThere's no members registered...\n");
        } else {
            System.out.println("List of members registered\n");
            for (int i = 0; i < controller.getMembers().size(); i++) {
                System.out.println((i + 1) + " Member: \n" + controller.getMembers().get(i));
            }
            System.out.println("Enter Member number to delete Member: ");

            int nr = readInteger();

            if (nr <= controller.getMembers().size()) {
                controller.removeMember(nr);
                System.out.println("\nMember deleted!\n");
            } else {
                System.out.println("Error! - Please try again\n");
            }
        }
    }

    //----------------------------------------------------------------------------------------------------------------
    // Cashier Menu

    public void cashierMenu() {
        int userChoice;
        do {
            System.out.println("""
                    Logged in as cashier
                     ------------------------------------
                    1. View Members List
                    2. Edit Member Payments
                    3. View Members in restance
                                        
                    9. Go back
                     """);
            userChoice = readInteger();
            scanner.nextLine();
            handlingCashierChoice(userChoice);
        } while (userChoice != 9);
    }

    public void handlingCashierChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> listMembersHasPaid();
            case 2 -> editMemberPayment();
            case 3 -> listMembersRestance();
            case 9 -> System.out.println("Going back\n");
            default -> System.out.println("""   
                    Could not handle input. Please try again
                    Choose menu item from 1-3
                    """);
        }
    }

    public void editMemberPayment() {
        ArrayList<Member> memberList = controller.getMembers();
        if (memberList.size() < 1) {
            printNoMemberFoundMsg();
        } else {
            System.out.println("Members List:");

            for (int i = 0; i < memberList.size(); i++) {
                System.out.println("[" + (i + 1) + "] \n" + memberList.get(i));
            }
            System.out.println("Enter a number to edit the members information:");
            int num = readInteger();
            Member editMember;
            scanner.nextLine();
            if (num - 1 >= controller.getMembers().size()) {
                System.out.println("Member ID not found!");
            } else {
                editMember = memberList.get(num - 1);
                System.out.println("You are editing " + editMember.getName());

                System.out.println("Current Activity Type: " + (editMember.getMemberType() ? "Active" : "Passive"));
                System.out.println("Please enter the new activity below");
                boolean legalActivity = false;
                while (!legalActivity) {
                    System.out.println("""
                            Select activity:
                            1. Active
                            2. Passive
                            """);
                    int actType = readInteger();
                    switch (actType) {
                        case 1 -> {
                            editMember.setMemberType(true);
                            legalActivity = true;
                        }
                        case 2 -> {
                            editMember.setMemberType(false);
                            legalActivity = true;
                        }
                        default -> System.out.println("Activity not found! Try again.");
                    }
                }

                System.out.println("Current payment status: " + (editMember.getHasPaid() ? "Paid" : "Not Paid"));
                System.out.println("Please enter the new payment status below");
                boolean legalPayment = false;
                while (!legalPayment) {
                    System.out.println("""
                            Select Payment Status:
                            1. Member has Paid
                            2. Member has not Paid
                            """);
                    int actType = readInteger();
                    switch (actType) {
                        case 1 -> {
                            editMember.setHasPaid(true);
                            legalPayment = true;
                        }
                        case 2 -> {
                            editMember.setHasPaid(false);
                            legalPayment = true;
                        }
                        default -> System.out.println("Payment option is not found! Try again.");
                    }
                }
            }

        }
    }

    public void listMembersRestance() {
        if (controller.getMembers().size() < 1) {
            printNoMemberFoundMsg();
        } else {
            ArrayList<Member> restanceMembers = controller.getMembersRestance();
            AsciiTable at = new AsciiTable();
            int nr = 1;
            at.addRule();
            at.addRow("No.", "ID", "Name", "Birthdate", "Email", "Address", "Phone Number", "Paid Status", "Annual Payment").setTextAlignment(TextAlignment.CENTER);
            int totalMissingProfit = controller.getMissingProfit();
            for (Member member : restanceMembers) {
                at.addRule();
                DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                at.addRow(nr, member.getMemberID(), member.getName(), (member.getDateOfBirth().format(formatDate)), member.getEmail(), member.getAddress(), member.getPhoneNumber(), (member.getHasPaid() ? "Paid" : "Not Paid"), (member.getMembershipAnnualPayment() + "kr")).setTextAlignment(TextAlignment.CENTER);
                nr++;
            }
            at.addRule();
            at.addRow(null, null, null, null, null, null, null, "Total profit:", (totalMissingProfit + "kr")).setTextAlignment(TextAlignment.CENTER);
            at.addRule();
            at.getRenderer().setCWC(new CWC_FixedWidth().add(5).add(10).add(30).add(15).add(30).add(35).add(15).add(15).add(18));
            String rend = at.render();
            System.out.println(rend + "\n");
        }
    }

    public void listMembersHasPaid() {
        if (controller.getMembers().size() < 1) {
            printNoMemberFoundMsg();
        } else {
            ArrayList<Member> membersPaid = controller.getMemberProfit();
            AsciiTable at = new AsciiTable();
            int nr = 1;
            at.addRule();
            at.addRow("No.", "ID", "Name", "Birthdate", "Email", "Address", "Phone Number", "Paid Status", "Annual Payment").setTextAlignment(TextAlignment.CENTER);
            int totalProfitEarnings = controller.getTotalProfit();
            for (Member member : membersPaid) {
                at.addRule();
                DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                at.addRow(nr, member.getMemberID(), member.getName(), (member.getDateOfBirth().format(formatDate)), member.getEmail(), member.getAddress(), member.getPhoneNumber(), (member.getHasPaid() ? "Paid" : "Not Paid"), (member.getMembershipAnnualPayment() + "kr")).setTextAlignment(TextAlignment.CENTER);
                nr++;
            }
            at.addRule();
            at.addRow(null, null, null, null, null, null, null, "Total profit:", (totalProfitEarnings + "kr")).setTextAlignment(TextAlignment.CENTER);
            at.addRule();
            at.getRenderer().setCWC(new CWC_FixedWidth().add(5).add(10).add(30).add(15).add(30).add(35).add(15).add(15).add(18));
            String rend = at.render();
            System.out.println(rend + "\n");
        }
    }


    //----------------------------------------------------------------------------------------------------------------
    // Coach Menu

    public void coachSelectMenu() {
        int userChoice;
        do {
            System.out.println("""
                    Select your trainer  age group
                     ------------------------------------
                    1. Junior (<18 years old)
                    2. Adult (18> years old)
                                        
                    9. Go back
                     """);
            userChoice = readInteger();
            scanner.nextLine();
            handlingCoachSelectChoice(userChoice);
        } while (userChoice != 9);
    }

    public void handlingCoachSelectChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> {
                assignedTrainer = controller.getTrainer("Junior");
                coachMenu();
            }
            case 2 -> {
                assignedTrainer = controller.getTrainer("Adult");
                coachMenu();
            }
            default -> System.out.println("""   
                    Could not handle input. Please try again
                    Choose menu item from 1-3
                    """);
        }
    }

    public void coachMenu() {
        int userChoice;
        do {
            System.out.println("""
                    Logged in as coach
                     ------------------------------------
                    1. Add swimming record
                    2. Edit swimming record(s)
                    3. View top 5 swimmers
                                        
                    9. Go back
                     """);
            userChoice = readInteger();
            scanner.nextLine();
            handlingCoachChoice(userChoice);
        } while (userChoice != 9);
    }

    public void handlingCoachChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> addRecordCoach();
            case 2 -> editCoachMethod();
            case 3 -> topViewMenu();
            case 9 -> System.out.println("Going back\n");
            default -> System.out.println("""   
                    Could not handle input. Please try again
                    Choose menu item from 1-3
                    """);
        }
    }

    public void topViewMenu() {
        int userChoice;
        do {
            System.out.println("""
                    Top 5 View
                     ------------------------------------
                    1. Butterfly
                    2. Crawl
                    3. Backcrawl
                    4. Breaststroke
                                        
                    9. Go back
                     """);
            userChoice = readInteger();
            scanner.nextLine();
            handlingTopView(userChoice);
        } while (userChoice != 9);
    }

    public void handlingTopView(int userChoice) {
        switch (userChoice) {
            case 1 -> viewTop5("Butterfly");
            case 2 -> viewTop5("Crawl");
            case 3 -> viewTop5("Backcrawl");
            case 4 -> viewTop5("Breaststroke");

            case 9 -> System.out.println("Going back\n");
            default -> System.out.println("""   
                    Could not handle input. Please try again
                    Choose menu item from 1-3
                    """);
        }
    }

    public void addRecordCoach() {
        ArrayList<Member> memberList = controller.getMembersCompSpecific(assignedTrainer.getTrainerGroup());
        if (memberList.size() < 1) {
            printNoMemberFoundMsg();
        } else {
            listMembersCoach();
            System.out.println("Select a member you want to add a record: ");
            int num = readInteger();
            Member editMember;
            scanner.nextLine();
            if (num - 1 >= controller.getMembers().size()) {
                System.out.println("Member ID not found!");
            } else {
                editMember = memberList.get(num - 1);
                System.out.println("You are adding a record for " + editMember.getName());

                System.out.println("Enter event name: ");
                String eventName = readString();

                System.out.println("Enter placement: ");
                int placement = readInteger();
                scanner.nextLine();

                System.out.println("Enter best time (in seconds): ");
                int bestTime = readInteger();
                scanner.nextLine();

                System.out.println("Select the swimming disciplin");
                boolean legalDisciplin = false;
                String disciplin = "";
                while (!legalDisciplin) {
                    System.out.println("""
                            Select disciplin:
                            1. Butterfly
                            2. Crawl
                            3. Backcrawl
                            4. Breaststroke
                            """);
                    int actType = readInteger();
                    switch (actType) {
                        case 1 -> {
                            disciplin = "Butterfly";
                            legalDisciplin = true;
                        }
                        case 2 -> {
                            disciplin = "Crawl";
                            legalDisciplin = true;
                        }
                        case 3 -> {
                            disciplin = "Backcrawl";
                            legalDisciplin = true;
                        }
                        case 4 -> {
                            disciplin = "Breaststroke";
                            legalDisciplin = true;
                        }
                        default -> System.out.println("Disciplin not found! Try again.");
                    }
                }

                controller.addRecord(editMember.getMemberID(), editMember.getName(), eventName, placement, bestTime, disciplin);
            }
        }

    }

    public void listMembersCoach() {
        ArrayList<Member> memberList = controller.getMembersComp();
        if (memberList.size() < 1) {
            printNoMemberFoundMsg();
        } else {
            AsciiTable at = new AsciiTable();
            int nr = 1;
            at.addRule();
            at.addRow("No.", "ID", "Name", "Birthdate", "Member Type", "Activity Type", "Membership", "Trainer").setTextAlignment(TextAlignment.CENTER);
            for (Member member : memberList) {
                if(controller.getTrainer(member.membershipType()) == assignedTrainer) {
                    at.addRule();
                    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    at.addRow(nr, member.getMemberID(), member.getName(), (member.getDateOfBirth().format(formatDate)), (member.getMemberType() ? "Active" : "Passive"), (member.isActivityType() ? "Competitor" : "Motionist"), member.membershipType(), controller.getTrainer(member.membershipType()).getName()).setTextAlignment(TextAlignment.CENTER);
                    nr++;
                }
            }
            at.addRule();
            at.getRenderer().setCWC(new CWC_FixedWidth().add(5).add(10).add(30).add(15).add(15).add(15).add(15).add(20));
            String rend = at.render();
            System.out.println(rend + "\n");
        }
    }

    public void editCoachMethod() {
        ArrayList<Competition> memberList = controller.getRecords();
        if (memberList.size() < 1) {
            printNoMemberFoundMsg();
        } else {
            viewTop5("all");
            int num = readInteger();
            Competition editMember;
            scanner.nextLine();
            if (num - 1 >= controller.getRecords().size()) {
                System.out.println("Records not found!");
            } else {
                editMember = memberList.get(num - 1);
                System.out.println("You are editing " + editMember.getName());

                System.out.println("Current Name: " + editMember.getName());
                System.out.println("Please enter the new NAME below");
                String newName = readString();
                if (!newName.isEmpty()) {
                    editMember.setName(newName);
                }
                System.out.println("Current Event name: " + editMember.getEventName());
                System.out.println("Please enter the new Event name below");
                String newEventName = readString();
                if (!newEventName.isEmpty()) {
                    editMember.setEventName(newEventName);
                }
                System.out.println("Current Placement: " + editMember.getPlacement());
                System.out.println("Please enter the new PLACEMENT below");
                String newPlacement = readString();
                if (!newPlacement.isEmpty()) {
                    editMember.setPlacement(Integer.parseInt(newPlacement));
                }
                System.out.println("Current Best time: " + editMember.getBestTime());
                System.out.println("Please enter the new BEST TIME below");
                String newBestTime = readString();
                if (!newBestTime.isEmpty()) {
                    editMember.setBestTime(Double.parseDouble(newBestTime));
            }
        }

    }
}

    public void viewTop5(String selectedDisciplin) {
        if (controller.getRecords().size() < 1) {
            printNoMemberFoundMsg();
        } else {
            ArrayList<Competition> recordMembers = controller.getMemberDisciplin(selectedDisciplin);
            Collections.sort(controller.getRecords(), new PlacementComparator().thenComparing(new TimeComparator()));
            AsciiTable at = new AsciiTable();
            int nr = 1;
            at.addRule();
            at.addRow("No.", "ID", "Name", "Event", "Placement", "Best Time", "Disciplin").setTextAlignment(TextAlignment.CENTER);
            for (Competition comp : recordMembers) {
                    at.addRule();
                    at.addRow(nr, comp.getMemberID(), comp.getName(), comp.getEventName(), comp.getPlacement(), comp.getBestTime(), comp.getDisciplin()).setTextAlignment(TextAlignment.CENTER);
                    nr++;
            }
            at.addRule();
            at.getRenderer().setCWC(new CWC_FixedWidth().add(5).add(10).add(25).add(30).add(14).add(14).add(15));
            String rend = at.render();
            System.out.println(rend + "\n");
        }
    }

    // ------------------------------------------------------------
    // SCANNER INPUT HANDLER
    public int readInteger() {
        while (!scanner.hasNextInt()) {
            String errorMsg = scanner.next();
            System.out.println("Invalid value \"" + errorMsg + "\" Please try again");
        }
        return scanner.nextInt();
    }
    public String readString() {
        return scanner.nextLine().toLowerCase();
    }


    private void printNoMemberFoundMsg(){
        System.out.println("No member found!");
    }

    // Fail message date formatter
    private void localeDateFailMsg(String dateInput) {
            try {
                DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                validDate =  LocalDate.parse(dateInput, formatDate);
            } catch(DateTimeParseException ex) {
                System.out.println("Invalid date entered! Please try again.");
                localeDateFailMsg(scanner.nextLine());
            }
    }
}