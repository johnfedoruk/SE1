package databaseLayer;

import java.util.ArrayList;
import java.util.Calendar;

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

        birdList.add(new Bird("0001", "bird1", "Experiment #1", getCalendar(2016,2,22), getCalendar(2016,2,22), "Female"));
        birdList.add(new Bird("0002", "bird2", "Experiment #2", getCalendar(2016,2,23), getCalendar(2016,2,24), "Male"));
        birdList.add(new Bird("0003", "bird3", "Experiment #3", getCalendar(2016,2,24), getCalendar(2016,2,25), "Female"));
        experimentList.add(new Experiment("Dying Bird", "Psychological","Group 1", getCalendar(2016,2,22), getCalendar(2016,2,22), "John,Gimli", "blahblah"));
        experimentList.add(new Experiment("Living Bird", "Suicidal","Group 2", getCalendar(2016,2,22), getCalendar(2016,2,22), "James,Angelo", "blahblah"));
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
    public ArrayList<Bird> searchBirds(String id,String name,String sex,String birthDate,
                                              String deathDate) {

        ArrayList<Bird> queryResult = new ArrayList<Bird>();

        Bird tempBird;
        /**This will be replaced with a simple sql statement**/
        for (int i = 0; i < birdList.size(); i++ )
        {
            tempBird = birdList.get(i);

            if(!id.equals("") && !tempBird.id.contains(id))
                break;

            if(!name.equals("") && !tempBird.name.contains(name))
                break;

            if(!sex.equals("") && !tempBird.sex.contains(sex))
                break;

            if(!birthDate.equals("") && !tempBird.birthDate.equals(birthDate))
                break;

            if(!deathDate.equals("") && !tempBird.deathDate.equals(deathDate))
                break;

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

        Experiment tempExperiment;

        /**This will be replaced with a simple sql statement**/
        for (int i = 0; i < experimentList.size(); i++ )
        {
            tempExperiment = experimentList.get(i);

            if(!studyTitle.equals("") && !tempExperiment.getStudyTitle().contains(studyTitle))
                break;

            if(!studyType.equals("") && !tempExperiment.getStudyType().contains(studyType))
                break;

            if(!groupWithinExperiment.equals("") && !tempExperiment.getGroupWithinExperiment().contains(groupWithinExperiment))
                break;

            if(!startDate.equals("") && !tempExperiment.getStartDate().toString().equals(startDate))
                break;

            if(!endDate.equals("") && !tempExperiment.getEndDate().toString().equals(endDate))
                break;

            queryResult.add(tempExperiment);

        }

        return queryResult;
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
