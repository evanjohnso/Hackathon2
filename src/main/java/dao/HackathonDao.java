package dao;

import datamodels.*;

import java.util.List;

public interface HackathonDao {
    //Create
    void addHack(Hackathon newHack);

    //Read
    Hackathon findHack(int hackId);
    List<Hackathon> getAllHacks();

    //Update
    void updateLocation(int hackId, String newLocation);

    //Delete
    void removeHack(int hackId);

}
