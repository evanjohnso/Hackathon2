package dao;


import datamodels.*;

public interface MembersDao {


    //Create
    void add(Members newFolk);

    //Read
    Members findById(int memberId);

    //Delete
    void removeMember(int memberId);
}
