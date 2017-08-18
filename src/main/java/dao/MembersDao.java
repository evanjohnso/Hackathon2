package dao;


import datamodels.*;

import java.util.List;

public interface MembersDao {


    //Create
    void add(Members newFolk);

    //Read
    Members findById(int memberId);
    List<Members> getAllMembersByTeam(int teamId);
    Members findByName(String findIt);

    //Update
    //Can't change, have to remove member and add a new one

    //Delete
    void removeMember(int memberId);
}
