package datamodels;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class HackathonTest {
    public Hackathon setUp() {
        return new Hackathon("Java", "Portland, OR");
    }
    public Team setUpTeam() {
        return new Team("Ging Squad", "One day we'll rull the world", 7);
    }

    @After
    public void tearDown() throws Exception {
        Hackathon.getInstances().clear();
    }

    @Test
    public void createNewHackathon_true() {
        Hackathon testHack = setUp();
        assertEquals(true, testHack instanceof Hackathon);
    }

    @Test
    public void createTwoHackathonsAndKeepTrackOfThemStatically_true() {
        Hackathon testHack = setUp();
        Hackathon hack2 = new Hackathon("Python", "Seattle, WA");
        assertEquals(2, Hackathon.getInstances().size());
    }

    @Test
    public void createAHackathonAndAddTeams_true() {
        Hackathon testHack = setUp();
        Team team1 = setUpTeam();
        Team team2 = setUpTeam();
        testHack.addTeam(team1);
        testHack.addTeam(team2);
        assertEquals(2, testHack.getNumberOfTeams());
    }
}