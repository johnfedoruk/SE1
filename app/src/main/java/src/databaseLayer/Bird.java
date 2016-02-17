package src.databaseLayer;

/**
 * Created by pure__000 on 2016-02-16.
 */
public class Bird {

    String id;
    String name;

    public Bird(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public String getId()
    {
        return id;
    }
}
