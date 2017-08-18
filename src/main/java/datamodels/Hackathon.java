package datamodels;

import java.util.ArrayList;
import java.util.List;

public class Hackathon {
    private String focus;
    private String location;
    private int id;

    public Hackathon(String focus, String location) {
        this.focus = focus;
        this.location = location;
    }

    //Setters
    public void setFocus(String focus) {
        this.focus = focus;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(int id) { this.id = id; }

    //Getters
    public String getLocation() {
        return location;
    }
    public String getFocus() {
        return focus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hackathon hackathon = (Hackathon) o;

        if (!focus.equals(hackathon.focus)) return false;
        return location.equals(hackathon.location);
    }

    @Override
    public int hashCode() {
        int result = focus.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }
}
