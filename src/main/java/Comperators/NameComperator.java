package Comperators;

import MemberClass.Member;

import java.util.Comparator;

public class NameComperator implements Comparator<Member> {

    @Override
    public int compare(Member name1, Member name2) {
        return name1.getName().compareTo(name2.getName());
    }
}