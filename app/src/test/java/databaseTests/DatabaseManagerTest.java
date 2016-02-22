package databaseTests;

import databaseLayer.DatabaseManager;
import databaseLayer.Bird;

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
    }

    @Test
    public void testFindBird()
    {
        Bird myBird = new Bird("0001", "bird1","Experiment #1",myManager.getCalendar(2016, 02, 22),myManager.getCalendar(2016, 02, 22),"Female");

        assertEquals(myBird.getId(), myManager.findBird("0001").getId());
        assertNotEquals(myBird, myManager.findBird("bird2"));
        assertNull(myManager.findBird("alshdg"));
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
}
