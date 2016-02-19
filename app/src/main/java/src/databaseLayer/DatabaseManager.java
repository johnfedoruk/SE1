package src.databaseLayer;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by pure__000 on 2016-02-16.
 */
public class DatabaseManager {

    ArrayList<Bird> birdList;
    ArrayList<Experiment> experimentList;

    public DatabaseManager()
    {
        birdList = new ArrayList<Bird>();
        experimentList = new ArrayList<Experiment>();

        birdList.add(new Bird("0001", "bird1","Experiment #1",new Date(2016,02,22),new Date(2016,02,23),"Female"));
        birdList.add(new Bird("0002", "bird2","Experiment #2",new Date(2016,02,23),new Date(2016,02,24),"Male"));
        birdList.add(new Bird("0003", "bird3","Experiment #3",new Date(2016,02,25),new Date(2016,02,26),"Female"));
        experimentList.add(new Experiment("Dying Bird","Psychological","Group 1",new Date(2016,02,22),new Date(2016,02,23),"John,Gimli","blahblah"));
        experimentList.add(new Experiment("Living Bird","Suicidal","Group 2",new Date(2016,02,22),new Date(2016,02,23),"James,Angelo","blahblah"));
    }

    public void addBird(Bird bird){
        this.birdList.add(bird);
    }
    public void addExperiment(Experiment exp){
        this.experimentList.add(exp);
    }

    public Bird findBird(String id, String name)
    {
        for(int i = 0; i < birdList.size(); i++)
        {
            if(birdList.get(i).getId().equals(id) && birdList.get(i).getName().equals(name)) {
                return birdList.get(i);
            }
        }

        return null;
    }
}
