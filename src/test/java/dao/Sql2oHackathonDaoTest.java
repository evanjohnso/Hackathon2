package dao;

import datamodels.Hackathon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oHackathonDaoTest {
    private Sql2oHackathonDao hackDao;
    private Connection server;

    @Before
    public void setUp() throws Exception {
        final String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o access = new Sql2o(connectionString, "", "");
        hackDao = new Sql2oHackathonDao(access);
        server = access.open();
    }

    @After
    public void tearDown() throws Exception {
        server.close();
    }

    //helper
    public Hackathon tester() {
        return new Hackathon("Java", "Portland, OR");
    }



    @Test
    public void addHack() throws Exception {
        Hackathon test = tester();
        hackDao.addHack(test);
        assertEquals(0, hackDao.getAllHacks().size());
    }

    @Test
    public void findHack() throws Exception {
    }

    @Test
    public void getAllHacks() throws Exception {
    }

    @Test
    public void updateHack() throws Exception {
    }

    @Test
    public void removeHack() throws Exception {
    }

}