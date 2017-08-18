package datamodels;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String teamBlurb;
    //created by database
    private int teamId;
    private int hackId;

    //Constructor
    public Team (String teamName, String teamBlurb, int hackId) {
        this.teamName = teamName;
        this.teamBlurb = teamBlurb;
        this.hackId = hackId;
    }

    //Setters
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamBlurb(String teamBlurb) {
        this.teamBlurb = teamBlurb;
    }

    public void setTeamId(int id) {
        teamId = id;
    }

    public void setHackId(int hack) {
        hackId = hack;
    }


    //Getters
    public String getTeamName() {
        return teamName;
    }

    public String getTeamBlurb() {
        return teamBlurb;
    }

    public int getTeamId() { return teamId; }

    public int getHackId() { return hackId; }


    //redefine equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (teamId != team.teamId) return false;
        if (hackId != team.hackId) return false;
        if (!teamName.equals(team.teamName)) return false;
        return teamBlurb.equals(team.teamBlurb);
    }

    @Override
    public int hashCode() {
        int result = teamName.hashCode();
        result = 31 * result + teamBlurb.hashCode();
        result = 31 * result + teamId;
        result = 31 * result + hackId;
        return result;
    }
}
