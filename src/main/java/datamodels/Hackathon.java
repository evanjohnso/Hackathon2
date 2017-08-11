package datamodels;

import java.util.ArrayList;
import java.util.List;

public class Hackathon {
    private String focus;
    private static List<Hackathon> instances = new ArrayList<>();
    private ArrayList<Team> teams = new ArrayList<>();
    private String location;

    public Hackathon(String focus, String location) {
        this.focus = focus;
        this.location = location;
        instances.add(this);
    }

    //Setters
    public void addTeam(Team additional) {
        teams.add(additional);
    }

    //Getters
    public static List<Hackathon> getInstances() {
        return instances;
    }
    public ArrayList<Team> getTeams() {
        return teams;
    }
    public int getNumberOfTeams() {
        return teams.size();
    }
    public String getLocation() {
        return location;
    }
    public String getFocus() {
        return focus;
    }

    public static Hackathon findHack(String focus) {
        Hackathon thisHack = null;
        for (Hackathon hack: instances) {
            String thisFocus = hack.getFocus();
            if (thisFocus.equals(focus)) {
                thisHack = hack;
            }
        }
        return thisHack;
    }
}
