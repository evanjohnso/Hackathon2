package datamodels;


import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String teamBlurb;
    private List<Members> theSquad = new ArrayList<Members>();


    //Constructor
    public Team (String teamName, String teamBlurb, int initialMembers) {
        this.teamName = teamName;
        this.teamBlurb = teamBlurb;
        setMembers(initialMembers);
    }

    //Setters
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamBlurb(String teamBlurb) {
        this.teamBlurb = teamBlurb;
    }

    public void setMembers(int additional) {
        for (int i = 0; i <= additional; i++) {
            theSquad.add(new Members());
        }

    }

    //Getters
    public String getTeamName() {
        return teamName;
    }

    public String getTeamBlurb() {
        return teamBlurb;
    }

    public int getMembers() {
        return theSquad.size();
    }

    public int getTheSquad() {
        return theSquad.size();
    }
}
