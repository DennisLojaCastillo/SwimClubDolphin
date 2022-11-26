import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    //TODO Opret hovedmenu

    //TODO Menu til Formand, Træner og Kassere

    private final Controller controller = new Controller();
    Scanner scanner = new Scanner(System.in);

    public void startProgram() {
        int userChoice = -1;
        controller.loadMembers();
        do {
            System.out.println("""
                     Delfin Swim Club
                     ------------------------------------
                    1. Add Member
                    2. See Member(s) List
                    3. Edit Member
                    4. Remove Member
                    9. Exit
                     """);
            userChoice = readInteger();
            scanner.nextLine();
            handlingUserChoice(userChoice);
        } while (userChoice != 9);
    }

    public void handlingUserChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> addMember();
            case 2 -> listMembers();
            case 3 -> editMember();
            case 4 -> deleteMember();
            case 9 -> {
                System.out.println("Logging out...");
                controller.saveMembers();
            }
            default -> System.out.println("""   
                    Could not handle input. Please try again
                    Choose menu item from 1-4
                    """);
        }
    }

    public void addMember() {
        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter e-mail: ");
        String email = scanner.nextLine();

        System.out.println("Enter Social security number: ");
        int socialSecurityNumber = readInteger();
        scanner.nextLine();

        System.out.println("Enter address: ");
        String address = scanner.nextLine();

        System.out.println("Enter phone number: ");
        int phoneNumber = readInteger();
        scanner.nextLine();

        boolean legalActivity = false;
        boolean activityType = false;
        while (!legalActivity) {
            System.out.println("""
                    Select activity:
                    1. Active
                    2. Passive
                    """);
            int actType = readInteger();
            switch (actType) {
                case 1 -> {
                    activityType = true;
                    legalActivity = true;
                }
                case 2 -> {
                    activityType = false;
                    legalActivity = true;
                }
                default -> System.out.println("Activity not found! Try again.");
            }
        }


        boolean legalMember = false;
        String selectedMembership = "";
        while (!legalMember) {
            System.out.println("""
                    Select membership type:
                    1. Junior under 18 years - 1.000,-  
                    2. Adult 18+ - 1.600,-
                    3. Senior 60+ - 1.200,- (25% Discount)  
                    """);
            int memberType = readInteger();
            switch (memberType) {
                case 1 -> {
                    selectedMembership = "Junior";
                    legalMember = true;
                }
                case 2 -> {
                    selectedMembership = "Adult";
                    legalMember = true;
                }
                case 3 -> {
                    selectedMembership = "Senior";
                    legalMember = true;
                }
                default -> System.out.println("Activity not found! Try again.");
            }
        }

        controller.addMember(name, email, socialSecurityNumber, address, phoneNumber, activityType, selectedMembership);

        System.out.println("Member registered!");

    }

    public void listMembers() {
        if (controller.getMembers().size() < 1) {
            System.out.println("No members found.");
        } else {
            System.out.println("List of members");
            for (Member member : controller.getMembers()) {
                System.out.println(member);
            }
        }
    }

    public void editMember() {
        ArrayList<Member> memberList = controller.getMembers();
        if (memberList.size() < 1) {
            System.out.println("No members found!");
        } else {
            System.out.println("Superhero List:");

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
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    editMember.setName(newName);
                }
                System.out.println("Current Email: " + editMember.getEmail());
                System.out.println("Please enter the new EMAIL name below");
                String newEmail = scanner.nextLine();
                if (!newEmail.isEmpty()) {
                    editMember.setEmail(newEmail);
                }

                System.out.println("Current Social Security Number: " + editMember.getSocialSecurityNumber());
                System.out.println("Please enter the new Social Security Number below");
                String newSNN = scanner.nextLine();
                if (!newSNN.isEmpty()) {
                    editMember.setSocialSecurityNumber(Integer.parseInt(newSNN));
                }

                System.out.println("Current address " + editMember.getAddress());
                System.out.println("Please enter the new address below");
                String newAddress = scanner.nextLine();
                if (!newAddress.isEmpty()) {
                    editMember.setAddress(newAddress);
                }

                System.out.println("Current Phone Number: " + editMember.getPhoneNumber());
                System.out.println("Please enter the new phone number below");
                String newPhoneNumber = scanner.nextLine();
                if (!newPhoneNumber.isEmpty()) {
                    editMember.setPhoneNumber(Integer.parseInt(newPhoneNumber));
                }

                System.out.println("Current Activity Type: " + editMember.getActivityType());
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
                            editMember.setActivityType(true);
                            legalActivity = true;
                        }
                        case 2 -> {
                            editMember.setActivityType(false);
                            legalActivity = true;
                        }
                        default -> System.out.println("Activity not found! Try again.");
                    }
                }

                System.out.println("Current Membership Type: " + editMember.getMembership());
                System.out.println("Please enter the new membership below");
                String newMemberShip = scanner.nextLine();
                boolean legalMembership = false;
                while (!legalMembership) {
                    System.out.println("""
                            Select membership type:
                            1. Junior under 18 years - 1.000,- \s
                            2. Adult 18+ - 1.600,-
                            3. Senior 60+ - 1.200,- (25% Discount) \s
                                """);
                    int actType = readInteger();
                    switch (actType) {
                        case 1 -> {
                            editMember.setMembership("Junior");
                            legalMembership = true;
                        }
                        case 2 -> {
                            editMember.setMembership("Adult");
                            legalMembership = true;
                        }
                        case 3 -> {
                            editMember.setMembership("Senior");
                            legalMembership = true;
                        }
                        default -> System.out.println("Membership not found! Try again.");
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

    // ------------------------------------------------------------
    // SCANNER INPUT HANDLER
    public int readInteger() {
        while (!scanner.hasNextInt()) {
            String errorMsg = scanner.next();
            System.out.println("Invalid value \"" + errorMsg + "\" Please try again");
        }
        return scanner.nextInt();
    }
}