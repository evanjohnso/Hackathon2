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
        assertEquals(7, testTeam.getTeamSize());
    }

    @Test
    public void addTeamMembersWithInformation_true() {
        Team testTeam = setUp();
        Members newGuy = new Members();
        newGuy.setNewMember("Evan", "Portland", 25);
        testTeam.addMember(newGuy);
        assertEquals(8, testTeam.getTeamSize());
    }

    @Test
    public void findTeamMembers_true() {
        Team testTeam = setUp();
        Members newGuy = new Members();
        newGuy.setNewMember("Evan", "Portland", 25);
        testTeam.addMember(newGuy);
        assertEquals(newGuy, testTeam.findMember("Evan") );
    }

    @Test
    public void removeTeamMembers_true() {
        Team testTeam = setUp();
        Members newGuy = new Members();
        newGuy.setNewMember("Evan", "Portland", 25);
        testTeam.addMember(newGuy);
        assertEquals(true, testTeam.removeMember("Evan"));
    }

}