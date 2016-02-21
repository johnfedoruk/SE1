package src.databaseLayer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by pure__000 on 2016-02-16.
 */
public class DatabaseManager {

    private static ArrayList<Bird> birdList;
    private static ArrayList<Experiment> experimentList;
    private Calendar cal;

    public DatabaseManager()
    {
        birdList = new ArrayList<Bird>();
        experimentList = new ArrayList<Experiment>();
        cal = Calendar.getInstance();

        birdList.add(new Bird("0001", "bird1","Experiment #1",getCalendar(2016,2,22),getCalendar(2016,2,22),"Female"));
        birdList.add(new Bird("0002", "bird2","Experiment #2",getCalendar(2016,2,23),getCalendar(2016,2,24),"Male"));
        birdList.add(new Bird("0003", "bird3","Experiment #3",getCalendar(2016,2,24),getCalendar(2016,2,25),"Female"));
        experimentList.add(new Experiment("Dying Bird","Psychological","Group 1",getCalendar(2016,2,22),getCalendar(2016,2,22),"John,Gimli","blahblah"));
        experimentList.add(new Experiment("Living Bird","Suicidal","Group 2",getCalendar(2016,2,22),getCalendar(2016,2,22),"James,Angelo","blahblah"));
    }

    public void addBird(Bird bird){
        this.birdList.add(bird);
    }
    public void addExperiment(Experiment exp){
        this.experimentList.add(exp);
    }
    public ArrayList<Bird> search() {
        ArrayList<Bird> queryResults = this.birdList;
        return queryResults;
    }
    public static Bird findBird(String id)
    {
        for(int i = 0; i < birdList.size(); i++)
        {
            if(birdList.get(i).getId().equals(id)) {
                return birdList.get(i);
            }
        }

        return null;
    }

    public ArrayList<Experiment> getExperiment(){
        return this.experimentList;
    }
    public Calendar getCalendar(int yyyy,int m, int dd){
        this.cal = Calendar.getInstance();
        this.cal.set(yyyy,m,dd);
        return this.cal;
    }
   /* public String getCalendarday(){
        Calendar cal = Calendar.getInstance();
        cal.set(1993,02,26);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(cal.getTime());
    }*/
    public ArrayList<Bird> getBird(){
        return this.birdList;
    }
}
