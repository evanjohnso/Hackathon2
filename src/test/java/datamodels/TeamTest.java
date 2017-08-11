package datamodels;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {
    public Team setUp() {
        return new Team("Ging Squad", "One day we'll rull the world", 7);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createNewTeamWithMembers_true() {
        Team testTeam = setUp();
        assertEquals(true, testTeam instanceof Team);
    }

    @Test
    public void createTeamAndCheckMembersArray() {
        Team testTeam = setUp();
        assertEquals(8, testTeam.getTheSquad());
    }

}