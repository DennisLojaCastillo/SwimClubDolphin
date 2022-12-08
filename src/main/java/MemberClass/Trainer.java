package MemberClass;

public class Trainer {
    private final String name;
    private final String trainerGroup;

    public Trainer(String name, String trainerGroup) {
        this.name = name;
        this.trainerGroup = trainerGroup;
    }

    public String getName() {
        return name;
    }

    public String getTrainerGroup() {
        return trainerGroup;
    }

}
