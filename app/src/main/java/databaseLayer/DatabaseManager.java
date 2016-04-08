package databaseLayer;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import domainObjects.Bird;
import domainObjects.Experiment;
import domainObjects.MedicalHistory;
import databaseLayer.DatabaseStub;


/**
 * Created by pure__000 on 2016-02-16.
 * The class that acts as an intermediary between the database and the other layers
 */
public class DatabaseManager {

    private boolean SQL_ON = false;
    private static DatabaseStub dbStub;
    private static DatabaseSQL dbSQL = null;

    /**
     * DatabaseManager
     * default constructor
     */
    public DatabaseManager(DatabaseHelper helpMe)
    {
        if(SQL_ON)
            this.dbSQL = new DatabaseSQL(helpMe);
        else
            this.dbStub = new DatabaseStub();
    }

    public void generateDatabase(SQLiteOpenHelper helpMe)
    {
        this.dbSQL = new DatabaseSQL(helpMe);
    }

    public void switchDatabases()
    {
        SQL_ON = !SQL_ON;
    }
    /**
     * clearDatabases
     * clears the array lists, for testing purposes only
     */
    public void clearDatabases()
    {
        if(SQL_ON) {
            dbSQL.clearDatabases();
        }
        else
            dbStub.clearDatabases();
    }

    /**
     * addBird
     * add bird to the bird list
     * @param bird the bird to be added
     */
    public void addBird(Bird bird){
        if(SQL_ON)
        {
            dbSQL.addBird(bird);
        }
        else
        {
            dbStub.addBird(bird);
        }
    }
    public void removeBird(String id){
        if (SQL_ON) {
            dbSQL.removeBird(id);
        }
        else
            dbStub.removeBird(id);
    }

    /**
     * addExperiment
     * add an experiment to the experiment list
     * @param exp the experiment to be added
     */
    public void addExperiment(Experiment exp) {
            dbStub.addExperiment(exp);
    }

    /**
     * findBird
     * finds a bird with a certain id
     * @param id the id of the bird to be found
     * @return either null (when bird is not found) or the Bird
     */
    public Bird findBird(String id)
    {
        if(SQL_ON)
        {
            return dbSQL.findBird(id);
        }
        else
        {
            return dbStub.findBird(id);
        }
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
    private ArrayList<Bird> searchBirds(String id, String name, String sex, String birthDate, String deathDate, String status)
    {
        if(SQL_ON)
        {
            return null;
            //return dbSQL.searchBirds(id, name, sex, birthDate, deathDate, status);
        }
        else
        {
            return null;
            //return dbStub.searchBirds(id, name, sex, birthDate, deathDate, status);
        }
    }
    private ArrayList<Bird> searchBirds(String id, String name, String sex, String birthDate, String deathDate)
    {
        if(SQL_ON)
        {
            return null;
            //return dbSQL.searchBirds(id, name, sex, birthDate, deathDate);
        }
        else {
            return null;
            //return dbStub.searchBirds(id, name, sex, birthDate, deathDate);
        }
    }
    public ArrayList<Bird> searchBirds(Bird inputBird) {

        if (SQL_ON) {
            return dbSQL.searchBirds(inputBird);
        } else {
            return dbStub.searchBirds(inputBird);
        }
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
    public ArrayList<Experiment> searchExperiments(String studyTitle, String studyType, String
                                                          groupWithinExperiment, String startDate,
                                                          String endDate) {

            return dbStub.searchExperiments(studyTitle, studyType, groupWithinExperiment, startDate, endDate);

    }

    public ArrayList<Experiment> searchExperiments(Experiment experiment)
    {
        return dbStub.searchExperiments(experiment);

    }


    /**
     * getExperiment
     * get the experiment list
     * @return experimentList
     */
    public ArrayList<Experiment> getExperiment(){

        return dbStub.getExperiment();

    }

    /**
     * getCalendar
     * generate and return a calendar made up of the parameters
     * @param yyyy the year for the calendar
     * @param MM the month for the calendar
     * @param dd the day for the calendar
     * @return the calendar generated
     */
    public Calendar getCalendar(int yyyy,int MM, int dd){
        if(SQL_ON)
        {
            return dbSQL.getCalendar(yyyy, MM, dd);
        }
        else
        {
            return dbStub.getCalendar(yyyy, MM, dd);
        }
    }

    /**
     * getBird
     * gets the bird list
     * @return birdList
     */
    public ArrayList<Bird> getBird(){

        if(SQL_ON)
        {
            return dbSQL.getBird();
        }
        else
        {
            return dbStub.getBird();
        }
    }

    public ArrayList<Bird> generateRelatives(String birdId)
    {
        ArrayList<Bird> relatives = new ArrayList<Bird>();

        String mom, dad;

        Bird currBird;

        if(birdId == null || birdId == "")
        {
            return relatives;
        }
        else
        {
            try {
                currBird = findBird(birdId);
            } catch (Exception e) {
                currBird = null;
            }
            if(currBird != null) {
                relatives.add(currBird);

                mom = currBird.getMom();
                dad = currBird.getDad();

                relatives.addAll(generateRelatives(mom));
                relatives.addAll(generateRelatives(dad));

                return relatives;
            }
            else
                return relatives;
        }
    }
}
