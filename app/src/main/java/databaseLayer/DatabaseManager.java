package databaseLayer;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by pure__000 on 2016-02-16.
 * The class that acts as an intermediary between the database and the other layers
 */
public class DatabaseManager {

    private static ArrayList<Bird> birdList;
    private static ArrayList<Experiment> experimentList;
    private Calendar cal;

    /**
     * DatabaseManager
     * default constructor
     */
    public DatabaseManager()
    {
        birdList = new ArrayList<Bird>();
        experimentList = new ArrayList<Experiment>();
        cal = Calendar.getInstance();

        birdList.add(new Bird("0001", "bird1", "Experiment #1", getCalendar(2016,2,22), getCalendar(2016,2,22), "Female"));
        birdList.add(new Bird("0002", "bird2", "Experiment #2", getCalendar(2016,2,23), getCalendar(2016,2,24), "Male"));
        birdList.add(new Bird("0003", "bird3", "Experiment #3", getCalendar(2016,2,24), getCalendar(2016,2,25), "Female"));
        experimentList.add(new Experiment("Dying Bird", "Psychological","Group 1", getCalendar(2016,2,22), getCalendar(2016,2,22), "John,Gimli", "blahblah"));
        experimentList.add(new Experiment("Living Bird", "Suicidal","Group 2", getCalendar(2016,2,22), getCalendar(2016,2,22), "James,Angelo", "blahblah"));
    }

    /**
     * addBird
     * add bird to the bird list
     * @param bird the bird to be added
     */
    public void addBird(Bird bird){
        this.birdList.add(bird);
    }

    /**
     * addExperiment
     * add an experiment to the experiment list
     * @param exp the experiment to be added
     */
    public void addExperiment(Experiment exp){
        this.experimentList.add(exp);
    }
    public ArrayList<Bird> search() {
        ArrayList<Bird> queryResults = this.birdList;
        return queryResults;
    }

    /**
     * findBird
     * finds a bird with a certain id
     * @param id the id of the bird to be found
     * @return either null (when bird is not found) or the Bird
     */
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

    /**
     * searchBirds
     *  Queries the birdList and returns all birds that match the definitions
     * @param id The id of the bird. If not defined, has length of zero.
     * @param name The name of the bird. If not defined, has length of zero.
     * @param sex The sex of the bird. If male, will be "male", if female, will be "female"
     *            if both will be "both", if undefined will have length of zero
     * @param birthDate The birth date in string format. Has the format "dd mm yyyy". If not defined
     *                 it will have a length of zero.
     * @param deathDate The death date in string format. Has the format "dd mm yyyy". If not defined
     *                  it will have a length of zero.
     * @return The query results
     */
    public ArrayList<Bird> searchBirds(String id, String name, String sex, String birthDate,
                                              String deathDate) {

        ArrayList<Bird> queryResult = new ArrayList<Bird>();

        Bird tempBird;

        boolean add;

        /**This will be replaced with a simple sql statement**/
        for (int i = 0; i < birdList.size(); i++ )
        {
            add = true;
            tempBird = birdList.get(i);

            if(!id.equals("") && !tempBird.id.contains(id))
                add = false;
            if(!name.equals("") && !tempBird.name.contains(name))
                add = false;
            if(!sex.equals("") && !tempBird.sex.equals(sex))
                add = false;
            if(!birthDate.equals("") && !tempBird.birthDate.equals(birthDate))
                add = false;
            if(!deathDate.equals("") && !tempBird.deathDate.equals(deathDate))
                add = false;

            if(add)
                queryResult.add(tempBird);

        }
        return queryResult;
    }

    /**
     * searchExperiments
     *  Queries the experimentList and returns all birds that match the definitions
     * @param studyTitle The title of the experiment. If undefined will have length of zero.
     * @param studyType The type of experiment. If undefined will have length of zero.
     * @param groupWithinExperiment The group within the experiment. If undefined will have length
     *                              of zero
     * @param startDate The start date of the experiment. Has the format "dd mm yyyy". If not defined
     *                 it will have a length of zero.
     * @param endDate The end date of the experiment. Has the format "dd mm yyyy". If not defined
     *                 it will have a length of zero.
     * @return The query results
     */
    public static ArrayList<Experiment> searchExperiments(String studyTitle, String studyType, String
                                                          groupWithinExperiment, String startDate,
                                                          String endDate) {
        ArrayList<Experiment> queryResult = new ArrayList<>();

        boolean add;

        Experiment tempExperiment;

        /**This will be replaced with a simple sql statement**/
        for (int i = 0; i < experimentList.size(); i++ )
        {
            add = true;
            tempExperiment = experimentList.get(i);

            if(!studyTitle.equals("") && !tempExperiment.getStudyTitle().contains(studyTitle))
                add = false;
            if(!studyType.equals("") && !tempExperiment.getStudyType().contains(studyType))
                add = false;
            if(!groupWithinExperiment.equals("") && !tempExperiment.getGroupWithinExperiment().contains(groupWithinExperiment))
                add = false;
            if(!startDate.equals("") && !tempExperiment.getStartDate().toString().equals(startDate))
                add = false;
            if(!endDate.equals("") && !tempExperiment.getEndDate().toString().equals(endDate))
                add = false;

            if(add)
                queryResult.add(tempExperiment);

        }

        return queryResult;
    }


    /**
     * getExperiment
     * get the experiment list
     * @return experimentList
     */
    public ArrayList<Experiment> getExperiment(){
        return this.experimentList;
    }

    /**
     * getCalendar
     * generate and return a calendar made up of the parameters
     * @param yyyy the year for the calendar
     * @param m the month for the calendar
     * @param dd the day for the calendar
     * @return the calendar generated
     */
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

    /**
     * getBird
     * gets the bird list
     * @return birdList
     */
    public ArrayList<Bird> getBird(){
        return this.birdList;
    }
}
