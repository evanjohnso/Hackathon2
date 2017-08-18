package dao;


import datamodels.*;

public interface TeamDao {

    //Create
    void addTeam(Team newSquad);

    //Read
    Team findById(int teamId);

    //Update
    void changeName(String newName, int teamId);

    //Delete
    void removeTeam(int teamId);

}
