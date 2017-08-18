package dao;

import datamodels.Members;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/18/17.
 */
public class Sql2oMemberDaoTest {
    private Sql2oMemberDao memberDao;
    private Connection server;

    @Before
    public void setUp() throws Exception {
        final String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o access = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(access);
        server = access.open();
    }
    @After
    public void tearDown() throws Exception {
        server.close();
    }


    //helper
    public Members tester() {
        return new Members("Evan", "PDX", 1, 1);
    }


    @Test
    public void addMemberPassesId() throws Exception {
        Members testing = tester();
        memberDao.add(testing);
        assertEquals(1, testing.getMemberId());

    }

    @Test
    public void getAllMembersByTeam() throws Exception {
        Members testing = tester();
        memberDao.add(testing);

        Members testing2 = tester();
        memberDao.add(testing2);

        Members testing3 = tester();
        memberDao.add(testing3);
        assertEquals(3, memberDao.getAllMembersByTeam(1).size());

    }

    @Test
    public void findById() throws Exception {
        Members testing = tester();
        memberDao.add(testing);

        Members testing2 = tester();
        memberDao.add(testing2);

        Members testing3 = tester();
        memberDao.add(testing3);
        assertEquals(testing3, memberDao.findById(testing3.getMemberId()));
    }

    @Test
    public void removeMember() throws Exception {
        Members testing = tester();
        memberDao.add(testing);

        Members testing2 = tester();
        memberDao.add(testing2);

        Members testing3 = tester();
        memberDao.add(testing3);
        memberDao.removeMember(testing2.getMemberId());
        assertEquals(2, memberDao.getAllMembersByTeam(1).size());
    }

    @Test
    public void findIdByName() throws Exception {
        Members testing = tester();
        memberDao.add(testing);
        Members newGuy = new Members("Pablo", "Seattle", 12,23);
        memberDao.add(newGuy);
        assertEquals(newGuy, memberDao.findByName("Pablo"));
    }

}