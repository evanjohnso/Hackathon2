package datamodels;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String teamBlurb;
    private ArrayList<Members> theSquad = new ArrayList<Members>();
    private static ArrayList<Team> instances = new ArrayList<Team>();


    //Constructor
    public Team (String teamName, String teamBlurb, int initialMembers) {
        this.teamName = teamName;
        this.teamBlurb = teamBlurb;
        //Add member skeletons to have information updated later closer to start date
        for (int i=0; i < initialMembers; i++)
            theSquad.add(new Members());
        instances.add(this);
    }

    //Setters
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamBlurb(String teamBlurb) {
        this.teamBlurb = teamBlurb;
    }

    public void addMember(Members additional) {
        this.theSquad.add(additional);
    }
    //This removeMember method does not work if you don't have a separate findMember method to call inside..you will get
    //concurrent modification exception
    //                          pass individual as object to be removed
    //      for (Members member: theSquad) {
//            if (member == individual)
                //    theSquad.remove(member);
                //Confused as to why this doesn't work

    public boolean removeMember(String theirName) {
        boolean removed = true;
        Members adios = findMember(theirName);
        if (adios != null)
            theSquad.remove(adios);
        else
            removed = false;
        return removed;
    }

    public Members findMember(String individualName) {
        Members found = null;
        for (Members member: theSquad) {
            String theirName = member.getMemberName();
            if (individualName.equals(theirName))
                found = member;
        }
        return found;
    }

    //Getters
    public String getTeamName() {
        return teamName;
    }

    public String getTeamBlurb() {
        return teamBlurb;
    }

    public int getTeamSize() {
        return theSquad.size();
    }

    public ArrayList<Members> getTheSquad() {
        return theSquad;
    }

    public static Team findTeam(String teamName) {
        Team theTeam = null;
        for (Team team: instances) {
            String theName = team.getTeamName();
            if (theName.equals(teamName))
                theTeam = team;
        }
        return theTeam;
    }
}
