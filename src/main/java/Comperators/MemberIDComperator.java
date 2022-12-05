package Comperators;

import MemberClass.Member;

import java.util.Comparator;

public class MemberIDComperator implements Comparator<Member> {

    @Override
    public int compare(Member id1, Member id2) {
        return id1.getMemberID().compareTo(id2.getMemberID());
    }
}
