package datamodels;

import java.util.ArrayList;
import java.util.List;

public class Hackathon {
    private String focus;



    private static List<Hackathon> instances = new ArrayList<>();

    public Hackathon(String focus) {
        this.focus = focus;
        instances.add(this);

    }

    //Setters

    //Getters
    public static List<Hackathon> getInstances() {
        return instances;
    }
}
