package dao;


import datamodels.*;

import java.util.List;

public interface TeamDao {

    //Create
    void addTeam(Team newSquad);

    //Read
    Team findById(int teamId);
    List<Team> getAllTeamsByHack(int hackID);

    //Update
    void changeName(String newName, int teamId);

    //Delete
    void removeTeam(int teamId);

}
