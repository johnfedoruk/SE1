package databaseLayer;

import java.util.ArrayList;
import java.util.Calendar;

import domainObjects.Bird;
import domainObjects.Experiment;
import domainObjects.MedicalHistory;

/**
 * Created by Kaj on 3/11/2016.
 */
public class DatabaseStub {
    private static ArrayList<Bird> birdList;
    private static ArrayList<Experiment> experimentList;
    private Calendar cal;

    /**
     * DatabaseManager
     * default constructor
     */
    public DatabaseStub()
    {
        birdList = new ArrayList<>();
        experimentList = new ArrayList<>();
        cal = Calendar.getInstance();

        birdList.add(new Bird("0001", "bird1", "Experiment #1", getCalendar(2016,2,22), getCalendar(2016,2,22), "Female",new MedicalHistory( getCalendar(2016, 2, 22),"Chicken pox","Tylenol","did not work"),true));
        birdList.add(new Bird("0002", "bird2", "Experiment #2", getCalendar(2016,2,23), getCalendar(2016,2,24), "Male",new MedicalHistory( getCalendar(2016, 2, 24),"Flu","Polysporin","did not work"),true));
        birdList.add(new Bird("0003", "bird3", "Experiment #3", getCalendar(2016,2,24), getCalendar(2016,2,25), "Female",new MedicalHistory( getCalendar(2016, 2, 25),"Down Syndrome","Chemotherapy","did not work"),true));
        experimentList.add(new Experiment("Dying Bird", "Psychological","Group 1", getCalendar(2016,2,22), getCalendar(2016,2,22), "John,Gimli", "blahblah",true));
        experimentList.add(new Experiment("Living Bird", "Suicidal","Group 2", getCalendar(2016,2,22), getCalendar(2016,2,22), "James,Angelo", "blahblah",false));

    }

    /**
     * clearDatabases
     * clears the array lists, for testing purposes only
     */
    public void clearDatabases()
    {
        birdList = new ArrayList<Bird>();
        experimentList = new ArrayList<Experiment>();
    }

    /**
     * addBird
     * add bird to the bird list
     * @param bird the bird to be added
     */
    public void addBird(Bird bird){
        this.birdList.add(bird);
    }
    public void removeBird(String id){
        id = id.trim().toLowerCase();
        for(int i=0;i<this.birdList.size();i++) {
            if(this.birdList.get(i).getId().toLowerCase().trim().equals(id)) {
                this.birdList.remove(i);
                return;
            }
        }
    }

    /**
     * addExperiment
     * add an experiment to the experiment list
     * @param exp the experiment to be added
     */
    public void addExperiment(Experiment exp) {
        this.experimentList.add(exp);
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
     * @param birthDate The birth date in string format. Has the format "yyyy-MM-dd". If not defined
     *                 it will have a length of zero.
     * @param deathDate The death date in string format. Has the format "yyyy-MM-dd". If not defined
     *                  it will have a length of zero.
     * @return The query results
     */
    public ArrayList<Bird> searchBirds(String id, String name, String sex, String birthDate, String deathDate, String status)
    {

        return searchBirds(new Bird(id, name, "", birthDate, deathDate, sex, status));

      /*  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar bd = Calendar.getInstance();
        Calendar dd = Calendar.getInstance();
        try {
            bd.setTime(simpleDateFormat.parse(birthDate));
        }
        catch(Exception e){bd = null;}
        try {
            dd.setTime(simpleDateFormat.parse(deathDate));
        }
        catch(Exception e){dd = null;}
        Bird bird = new Bird(id,name,"",bd,dd,sex,null);
        return searchBirds(bird);
*/
    }

    public ArrayList<Bird> searchBirds(String id, String name, String sex, String birthDate, String deathDate) {

        return searchBirds(new Bird(id, name, "", birthDate, deathDate, sex, ""));
    }

    public ArrayList<Bird> searchBirds(Bird inputBird) {

        ArrayList<Bird> queryResult = new ArrayList<>(this.birdList);
        String id = inputBird.getId().trim().toLowerCase();
        String name = inputBird.getName().trim().toLowerCase();
        String sex = inputBird.getSex().trim().toLowerCase();
        String birthDate = inputBird.getDateString(inputBird.getBirthDate()).trim().toLowerCase();
        String deathDate = inputBird.getDateString(inputBird.getDeathDate()).trim().toLowerCase();

        /**This will be replaced with a simple sql statement**/
        for(int i=queryResult.size()-1;i>=0;i--) {
            if(id!=null&&id.length()>0&&
                    !queryResult.get(i).getId().trim().toLowerCase().equals(id)) {
                queryResult.remove(i);
                continue;
            }
            if(name!=null&&name.length()>0&&
                    !queryResult.get(i).getName().trim().toLowerCase().equals(name)) {
                queryResult.remove(i);
                continue;
            }
            if(sex!=null&&sex.length()>0&&
                    !queryResult.get(i).getSex().trim().toLowerCase().equals(sex)) {
                queryResult.remove(i);
                continue;
            }
            if(birthDate!=null&&birthDate.length()>0&&
                    !queryResult.get(i).getDateString(queryResult.get(i).getBirthDate())
                            .equals(birthDate)) {
                queryResult.remove(i);
                continue;
            }
            if(deathDate!=null&&deathDate.length()>0&&
                    !queryResult.get(i).getDateString(queryResult.get(i).getDeathDate())
                            .equals(deathDate)) {
                queryResult.remove(i);
                continue;
            }
        }
        queryResult.trimToSize();
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

            if(!studyTitle.equals("") && !tempExperiment.getStudyTitle().toLowerCase()
                    .contains(studyTitle.toLowerCase()))
                add = false;
            if(!studyType.equals("") && !tempExperiment.getStudyType().toLowerCase()
                    .contains(studyType.toLowerCase()))
                add = false;
            if(!groupWithinExperiment.equals("") && !tempExperiment.getGroupWithinExperiment()
                    .toLowerCase().contains(groupWithinExperiment.toLowerCase()))
                add = false;
            if(!startDate.equals("") && !tempExperiment.getDateString(tempExperiment.getStartDate())
                    .equals(startDate))
                add = false;
            if(!endDate.equals("") && !tempExperiment.getDateString(tempExperiment.getEndDate())
                    .equals(endDate))
                add = false;

            if(add)
                queryResult.add(tempExperiment);

        }

        return queryResult;
    }

    public static ArrayList<Experiment> searchExperiments(Experiment experiment)
    {
        return searchExperiments(experiment.getStudyTitle(), experiment.getStudyType(), experiment.getGroupWithinExperiment(), experiment.getDateString(experiment.getStartDate()), experiment.getDateString(experiment.getEndDate()));
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

    /**
     * getBird
     * gets the bird list
     * @return birdList
     */
    public ArrayList<Bird> getBird(){
        return this.birdList;
    }
}

