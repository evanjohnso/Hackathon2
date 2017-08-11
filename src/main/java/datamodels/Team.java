package datamodels;


import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String teamBlurb;
    private ArrayList<Members> theSquad = new ArrayList<Members>();


    //Constructor
    public Team (String teamName, String teamBlurb, int initialMembers) {
        this.teamName = teamName;
        this.teamBlurb = teamBlurb;
        //Add member skeletons to have information updated later closer to start date
        for (int i=0; i <= initialMembers; i++)
            theSquad.add(new Members());
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

    public boolean removeMember(Members ditchEm) {
        boolean removed = true;
        Members adios = findMember(ditchEm);
        if (adios != null)
            theSquad.remove(adios);
        else
            removed = false;
        return removed;
    }

    public Members findMember(Members individual) {
        Members found = null;
        for (Members member: theSquad) {
            if (member == individual)
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
}
