package datamodels;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class HackathonTest {
    public Hackathon setUp() {
        return new Hackathon("Java");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createNewHackathon_true() {
        Hackathon testHack = setUp();
        assertEquals(true, testHack instanceof Hackathon);
    }

    @Test
    public void createTwoHackathonsAndKeepTrackOfThemStatically_true() {
        Hackathon testHack = setUp();
        Hackathon hack2 = new Hackathon("Python");
        assertEquals(2, Hackathon.getInstances().size());
    }
}