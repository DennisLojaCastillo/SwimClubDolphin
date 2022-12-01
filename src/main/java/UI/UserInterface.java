package UI;

import Database.Controller;
import MemberClass.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    //TODO Tilføj attributter i competitor and exerciser klasser!
    //TODO Edit Metode = newDate virker ikke!!!!!

    private final Scanner scanner;
    private final Controller controller;
    LocalDate validDate = null;

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
            case 3 -> System.out.println("Coach Management Method here!");
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
            case 9 -> {
                System.out.println("Going back\n");
            }
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

                default -> System.out.println("Activity nit found! Try again.");
            }
        }

        controller.addMember(name, dateOfBirth, email, address, phoneNumber, memberType, activityType, false);

        System.out.println("Member registered!");

    }

    public void listMembers() {
        if (controller.getMembers().size() < 1) {
            printNoMemberFoundMsg();
        } else {
            int totalProfit = 0;
            System.out.println("List of members");
            for (Member member : controller.getMembers()) {
                System.out.println(member);
                totalProfit += member.getMembershipAnnualPayment();
            }


            System.out.println("Total profit: " + totalProfit + "kr.");
        }
    }

    public void editMember() {
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
                    4. View Total gross profit
                    
                    9. Go back
                     """);
            userChoice = readInteger();
            scanner.nextLine();
            handlingCashierChoice(userChoice);
        } while (userChoice != 9);
    }

    public void handlingCashierChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> listMembers();
            case 2 -> editMemberPayment();
            //case 3 -> listMembersRestance();
            case 9 -> {
                System.out.println("Going back\n");
            }
            default -> System.out.println("""   
                    Could not handle input. Please try again
                    Choose menu item from 1-3
                    """);
        }
    }

    public void editMemberPayment () {
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
                System.out.println("Current payment status" + editMember.getHasPaid());
                System.out.println("Please enter the new payment status below");
                boolean legalPayment = false;
                while (!legalPayment){
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
                            legalPayment = false;
                        }
                        default -> System.out.println("Payment option is not found! Try again.");
                    }
                }
            }
        }
    }

    //----------------------------------------------------------------------------------------------------------------
    // Coach Menu





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

    //TODO Work in progress
    private String localeDateFailMsg(String dateInput) {
        boolean correctDate = false;
        while (!correctDate) {
            try {
                DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                validDate =  LocalDate.parse(dateInput, formatDate);
                break;
            } catch(DateTimeParseException ex) {
                System.out.println("Invalid date entered!");
                localeDateFailMsg(scanner.nextLine());
                break;
            }
        }
        return null;
    }
}
