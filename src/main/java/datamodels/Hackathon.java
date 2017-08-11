package datamodels;

import java.util.ArrayList;
import java.util.List;

public class Hackathon {
    private String focus;
    private static List<Hackathon> instances = new ArrayList<>();
    private static ArrayList<Team> teams = new ArrayList<>();

    public Hackathon(String focus) {
        this.focus = focus;
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
}
