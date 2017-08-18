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
        assertEquals(1, hackDao.getAllHacks().size());
    }

    @Test
    public void addHackAddsIdProperly() throws  Exception {
        Hackathon test = tester();
        hackDao.addHack(test);

        Hackathon test2 = tester();
        hackDao.addHack(test2);

        Hackathon test3 = tester();
        hackDao.addHack(test3);
        assertEquals(3, test3.getId());
    }

    @Test
    public void findHack() throws Exception {
        Hackathon test = tester();
        hackDao.addHack(test);

        Hackathon test2 = tester();
        hackDao.addHack(test2);

        Hackathon test3 = tester();
        hackDao.addHack(test3);
        assertEquals(test3, hackDao.findHack(test3.getId()));
    }

    @Test
    public void updateHack() throws Exception {
        Hackathon test = tester();
        hackDao.addHack(test);

        Hackathon test2 = tester();
        hackDao.addHack(test2);
        hackDao.updateLocation(test2.getId(), "Seattle");
        Hackathon updated = hackDao.findHack(test2.getId());
        assertEquals("Seattle", updated.getLocation());

    }

    @Test
    public void removeHack() throws Exception {
        Hackathon test = tester();
        hackDao.addHack(test);

        Hackathon test2 = tester();
        hackDao.addHack(test2);
        hackDao.removeHack(test.getId());
        assertEquals(1, hackDao.getAllHacks().size());

    }

}