package domainObjects;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jose Matundan on 3/12/2016.
 */
//For interacting with the Calendar object
public class DateParser
{
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    public DateParser()
    {
        Calendar cal = Calendar.getInstance();
    }

    public String getDateString(Calendar cal)
    {
        return sdf.format(cal.getTime());
    }

    public Calendar toCalendar(String date)
    {
        String buffer[] = date.split("-");
        this.cal.set(Integer.parseInt(buffer[2]),Integer.parseInt(buffer[1]),Integer.parseInt(buffer[0]));
        return this.cal;
    }

    public String setDateString(String date)
    {
        String buffer[] = date.split("-");
        this.cal.set(Integer.parseInt(buffer[2]),Integer.parseInt(buffer[1]),Integer.parseInt(buffer[0]));
        return sdf.format(this.cal);
    }



}
