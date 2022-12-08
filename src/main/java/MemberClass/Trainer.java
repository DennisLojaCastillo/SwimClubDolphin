package MemberClass;

public class Trainer {
    private String name;
    private int phoneNumber;
    private String email;
    private String trainerGroup;

    public Trainer(String name, int phoneNumber, String email, String trainerGroup) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.trainerGroup = trainerGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTrainerGroup() {
        return trainerGroup;
    }

    public void setTrainerGroup(String trainerGroup) {
        this.trainerGroup = trainerGroup;
    }
}
