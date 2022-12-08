package MemberClass;

public class Competition extends Member {
    private String eventName;
    private int placement;
    private double bestTime;

    public Competition(String memberID, String name, String eventName, int placement, double bestTime) {
        super(memberID, name);
        this.eventName = eventName;
        this.placement = placement;
        this.bestTime = bestTime;
    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    public double getBestTime() {
        return bestTime;
    }

    public void setBestTime(double bestTime) {
        this.bestTime = bestTime;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "memberID='" + super.getMemberID() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", eventName='" + eventName + '\'' +
                ", placement=" + placement +
                ", bestTime=" + bestTime +
                '}';
    }
}



