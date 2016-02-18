package src.databaseLayer;

import java.util.ArrayList;

/**
 * Created by pure__000 on 2016-02-16.
 */
public class DatabaseManager {

    ArrayList<Bird> birdList;

    public DatabaseManager()
    {
        birdList = new ArrayList<Bird>();

        birdList.add(new Bird("0001", "bird1"));
        birdList.add(new Bird("0002", "bird2"));
        birdList.add(new Bird("0003", "bird3"));
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
