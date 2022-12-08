package Comparators;
import MemberClass.*;
import java.util.Comparator;

public class TimeComparator implements Comparator<Competition> {
    @Override
    public int compare(Competition c1, Competition c2) {
        return (int) (c1.getBestTime() - c2.getBestTime());
    }
}
