package businessLogicTests;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.TestResult;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import businessLogicLayer.DateParser;

/**
 * Created by Divina on 3/14/2016.
 */
public class DateParserTest extends ApplicationTestCase<Application>
{
    public DateParserTest(Class<Application> applicationClass) {
        super(applicationClass);
    }
    DateParser d;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    @Before
    public void init()
    {
        d = new DateParser();
    }

    @Test
    public void testGetDateString()
    {
        Calendar cal = Calendar.getInstance();
        Calendar caltest = Calendar.getInstance();
        assertEquals(sdf.format(caltest.getTime()),d.getDateString(cal));
    }

    public void testToCalendar()
    {
        String date = "2-3-2016";
        Calendar cal = Calendar.getInstance();
        String buffer[] = date.split("-");
        cal.set(Integer.parseInt(buffer[2]),Integer.parseInt(buffer[1]),Integer.parseInt(buffer[0]));
        assertEquals(cal,d.toCalendar(date));
    }
    @Override
    protected TestResult createResult() {
        return super.createResult();
    }
}
