public class Member {
    private String name;
    private String email;
    private int socialSecurityNumber;
    private String address;
    private int phoneNumber;
    private boolean activityType;
    private String membership;

    public Member(String name, String email, int socialSecurityNumber, String address, int phoneNumber, boolean activityType, String membership) {
        this.name = name;
        this.email = email;
        this.socialSecurityNumber = socialSecurityNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.activityType = activityType;
        this.membership = membership;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getActivityType() {
        return activityType;
    }

    public void setActivityType(boolean activityType) {
        this.activityType = activityType;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    @Override
    public String toString() {
        return "Name: " + name
                + "\nEmail: " + email
                + "\nSocial Security Number: " + socialSecurityNumber
                + "\nAddress: " + address
                + "\nPhone Number: " + phoneNumber
                + "\nActivity Type: " + (activityType ? "Aktiv" : "Passiv")
                + "\nMembership: " + membership + "\n";
    }
}
