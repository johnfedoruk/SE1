package test.databaseTests;

import src.databaseLayer.DatabaseManager;
import src.databaseLayer.Bird;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Created by pure__000 on 2016-02-16.
 */
public class DatabaseManagerTest extends TestCase{

    DatabaseManager myManager;

    //@Test
    public void testFindBird()
    {
        myManager = new DatabaseManager();

        Bird myBird = new Bird("0001", "bird1");

        assertEquals(myBird, myManager.findBird("0001", "bird1"));
        assertNotSame(myBird, myManager.findBird("0002", "bird1"));
    }

}
