package datamodels;

import java.util.ArrayList;
import java.util.List;

public class Members {
    private String memberName;
    //Use this for later with a database && SQL and making some aggregate data
    private String memberLocation;
    private int memberAge;
    private List<Members> eventTotal = new ArrayList<Members>();

    //Constructor
    public Members() {
        eventTotal.add(this);

    }

    //Setters
    //Allow administrator to create a new member with all info in form later
    public void setNewMember(String name, String location, int age) {
        memberName = name;
        memberLocation = location;
        memberAge = age;
    }
    //Getters
    public String getMemberName() {
        return memberName;
    }

    public String getMemberLocation() {
        return memberLocation;
    }

    public int getMemberAge() {
        return memberAge;
    }
}
