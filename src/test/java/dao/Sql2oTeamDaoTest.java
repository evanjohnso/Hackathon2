package dao;

import datamodels.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/18/17.
 */
public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
    private Connection server;

    @Before
    public void setUp() throws Exception {
        final String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o access = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(access);
        server = access.open();
    }
    @After
    public void tearDown() throws Exception {
        server.close();
    }
    //helper
    public Team tester() {
        return new Team("Squad Goalz", "Best of the west", 1);
    }


    @Test
    public void addTeamPassesId() throws Exception {
        Team test = tester();
        teamDao.addTeam(test);
        assertEquals(1, test.getTeamId());
    }

    @Test
    public void addTeamAndGetAll() throws Exception {
        Team test = tester();
        teamDao.addTeam(test);

        Team test2 = tester();
        teamDao.addTeam(test2);
        assertEquals(2, teamDao.getAllTeamsByHack(1).size());
    }

    @Test
    public void findById() throws Exception {
        Team test = tester();
        teamDao.addTeam(test);

        Team test2 = tester();
        teamDao.addTeam(test2);
        assertEquals(test2, teamDao.findById(test2.getTeamId()));
    }

    @Test
    public void changeName() throws Exception {
    }

    @Test
    public void removeTeam() throws Exception {
    }

}