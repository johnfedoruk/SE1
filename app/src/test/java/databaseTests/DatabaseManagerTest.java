package databaseTests;

import databaseLayer.DatabaseManager;
import databaseLayer.Bird;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Calendar;

/**
 * Created by pure__000 on 2016-02-16.
 */
public class DatabaseManagerTest {

    DatabaseManager myManager;
    Calendar date = Calendar.getInstance();

    @Test
    public void testFindBird()
    {
        myManager = new DatabaseManager();

        Bird myBird = new Bird("0001", "bird1","Experiment #1",myManager.getCalendar(2016, 02, 22),myManager.getCalendar(2016, 02, 22),"Female");

        assertEquals(myBird, myManager.findBird("bird1"));
        assertNotEquals(myBird, myManager.findBird("bird2"));
    }


}
