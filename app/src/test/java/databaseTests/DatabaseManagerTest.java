package databaseTests;

import databaseLayer.DatabaseManager;
import databaseLayer.Bird;
import databaseLayer.Experiment;
import databaseLayer.MedicalHistory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

/**
 * Created by pure__000 on 2016-02-16.
 */
public class DatabaseManagerTest {

    DatabaseManager myManager;
    Calendar date = Calendar.getInstance();
    @Before
    public void init()
    {
        myManager = new DatabaseManager();

        myManager.clearDatabases();

        myManager.addBird(new Bird("0001", "bird1", "Experiment #1", myManager.getCalendar(2016, 2, 22), myManager.getCalendar(2016, 2, 22), "Female", new MedicalHistory(myManager.getCalendar(2016, 2, 27), "AIDS", "Tylenol", "did not work")));
        myManager.addBird(new Bird("0002", "bird2", "Experiment #2", myManager.getCalendar(2016, 2, 23), myManager.getCalendar(2016, 2, 24), "Male",new MedicalHistory( myManager.getCalendar(2016, 2, 28),"Herpes","Tylenol","did not work")));
        myManager.addBird(new Bird("0003", "bird3", "Experiment #3", myManager.getCalendar(2016, 2, 24), myManager.getCalendar(2016,2,25), "Female",new MedicalHistory( myManager.getCalendar(2016, 2, 29),"HIV","Tylenol","did not work")));
        myManager.addExperiment(new Experiment("Dying Bird", "Psychological", "Group 1", myManager.getCalendar(2016, 2, 22), myManager.getCalendar(2016, 2, 22), "John,Gimli", "blahblah"));
        myManager.addExperiment(new Experiment("Living Bird", "Suicidal", "Group 2", myManager.getCalendar(2016, 2, 22), myManager.getCalendar(2016, 2, 22), "James,Angelo", "blahblah"));
        myManager.addExperiment(new Experiment("Living Bird", "Suicidal", "Group 2", myManager.getCalendar(2016, 2, 22), myManager.getCalendar(2016, 2, 24), "James,Angelo", "blahblah"));

    }

    @Test
    public void testAddBird() throws Exception
    {
        //NULL test case
        myManager.addBird(new Bird(null, null, null, myManager.getCalendar(0, 0, 0), myManager.getCalendar(0, 0, 0), null, new MedicalHistory(myManager.getCalendar(0, 0, 0), null, null, null)));
    }

    public void testAddExperiment()
    {

    }

    @Test
    public void testFindBird()
    {
        Bird myBird = new Bird("0001", "bird1","Experiment #1",myManager.getCalendar(2016, 02, 22),myManager.getCalendar(2016, 02, 22),"Female",new MedicalHistory( myManager.getCalendar(2016, 2, 22),"Glaucoma","Tylenol","did not work"));

        assertEquals(myBird.getId(), myManager.findBird("0001").getId());
        assertNotEquals(myBird, myManager.findBird("bird2"));
        assertNull(myManager.findBird("nonExistantBird"));
    }

    @Test
    public void testSearchBirdByName()
    {
        assertEquals(3, myManager.searchBirds("", "", "", "", "").size());
        assertEquals(1, myManager.searchBirds("", "bird1", "", "", "").size());
        assertEquals(0, myManager.searchBirds("", "nonExistantName", "", "", "").size());
        assertNotNull(myManager.searchBirds("", "", "", "", ""));
    }

    @Test
    public void testSearchBirdBySex()
    {
        assertEquals(2, myManager.searchBirds("", "", "Female", "", "").size());
        assertEquals(1, myManager.searchBirds("", "", "Male", "", "").size());
        assertEquals(0, myManager.searchBirds("", "", "nonExistantSex", "", "").size());
        assertNotNull(myManager.searchBirds("", "", "", "", ""));
    }

    @Test
    public void testSearchBirdByDate()
    {
        assertEquals(1, myManager.searchBirds("", "", "", "2016-03-22", "").size());
        assertEquals(1, myManager.searchBirds("", "", "", "", "2016-03-24").size());
        assertEquals(3, myManager.searchBirds("", "", "", "", "").size());
        assertNotNull(myManager.searchBirds("", "", "", "", ""));
    }

    @Test
    public void testSearchExperimentByTitle()
    {
        assertEquals(1, myManager.searchExperiments("Dying Bird", "", "", "", "").size());
        assertEquals(2, myManager.searchExperiments("Living Bird", "", "", "", "").size());
        assertEquals(3, myManager.searchExperiments("i", "", "", "", "").size());
        assertEquals(0, myManager.searchExperiments("nonExistantExperiment", "", "", "", "").size());
        assertNotNull(myManager.searchExperiments("", "", "", "", ""));
    }

    @Test
    public void testSearchExperimentByType()
    {
        assertEquals(2, myManager.searchExperiments("", "Suicidal", "", "", "").size());
        assertEquals(1, myManager.searchExperiments("", "Psychological", "", "", "").size());
        assertEquals(3, myManager.searchExperiments("", "al", "", "", "").size());
        assertEquals(0, myManager.searchExperiments("", "nonExistantType", "", "", "").size());
        assertNotNull(myManager.searchExperiments("", "", "", "", ""));
    }

    @Test
    public void testSearchExperimentByGroup()
    {
        assertEquals(1, myManager.searchExperiments("", "", "Group 1", "", "").size());
        assertEquals(2, myManager.searchExperiments("", "", "Group 2", "", "").size());
        assertEquals(3, myManager.searchExperiments("", "", "Group", "", "").size());
        assertEquals(0, myManager.searchExperiments("", "", "nonExistantGroup", "", "").size());
        assertNotNull(myManager.searchExperiments("", "", "", "", ""));
    }
}
