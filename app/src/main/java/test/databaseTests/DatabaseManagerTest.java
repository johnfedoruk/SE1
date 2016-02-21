package test.databaseTests;

import src.databaseLayer.DatabaseManager;
import src.databaseLayer.Bird;
import junit.framework.Test;
import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by pure__000 on 2016-02-16.
 */
public class DatabaseManagerTest extends TestCase{

    DatabaseManager myManager;
    Calendar date = Calendar.getInstance();
    //@Test
    public void testFindBird()
    {
        myManager = new DatabaseManager();

        Bird myBird = new Bird("0001", "bird1","Experiment #1",myManager.getCalendar(2016, 02, 22),myManager.getCalendar(2016, 02, 22),"Female");

        assertEquals(myBird, myManager.findBird("bird1"));
        assertNotSame(myBird, myManager.findBird("bird2"));
    }


}
