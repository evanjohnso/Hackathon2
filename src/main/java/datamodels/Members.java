package datamodels;

import java.util.ArrayList;
import java.util.List;

public class Members {
    private int hackId;
    private int teamId;
    private int memberId;
    private String memberName;
    private String memberLocation;

    //Constructor
    public Members(String name, String location, int teamId, int hackId) {
        memberName = name;
        memberLocation = location;
        this.teamId = teamId;
        this.hackId = hackId;
    }

    //Setters
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    public void setHackId(int hackId) {
        this.hackId = hackId;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public void setMemberLocation(String memberLocation) {
        this.memberLocation = memberLocation;
    }

    //Getters
    public int getMemberId() {
        return memberId;
    }
    public int getHackId() {
        return hackId;
    }
    public int getTeamId() {
        return teamId;
    }
    public String getMemberName() {
        return memberName;
    }
    public String getMemberLocation() {
        return memberLocation;
    }

    //Redefine Equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Members members = (Members) o;

        if (teamId != members.teamId) return false;
        if (!memberName.equals(members.memberName)) return false;
        return memberLocation.equals(members.memberLocation);
    }

    @Override
    public int hashCode() {
        int result = teamId;
        result = 31 * result + memberName.hashCode();
        result = 31 * result + memberLocation.hashCode();
        return result;
    }
}
