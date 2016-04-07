package databaseLayer;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Calendar;

import businessLogicLayer.MainActivity;
import databaseLayer.schemas.BirdDatabase;
import databaseLayer.schemas.ExperimentDatabase;
import domainObjects.Bird;
import domainObjects.Experiment;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by Kaj on 3/7/2016.
 */
public class DatabaseSQL{

    static BirdDatabase birdTable;
    static ExperimentDatabase expTable;
    private Calendar cal;
    private SQLiteOpenHelper helpThisGuy;

    public DatabaseSQL(SQLiteOpenHelper helpPleaseSomebody)
    {
        birdTable = new BirdDatabase(helpPleaseSomebody);
        expTable = new ExperimentDatabase(helpPleaseSomebody);
        helpThisGuy = helpPleaseSomebody;
    }

    public void clearDatabases()
    {
        SQLiteDatabase dirtyDB = helpThisGuy.getWritableDatabase();
        dirtyDB.execSQL(BirdDatabase.DELETE_ENTRIES);
        dirtyDB.execSQL(ExperimentDatabase.DELETE_ENTRIES);
    }

    public void addBird(Bird values)
    {
        birdTable.insert(values);
    }

    public void removeBird(String id){
        birdTable.removeBird(id);
    }
    /**
     * addExperiment
     * add an experiment to the experiment list
     * @param exp the experiment to be added
     */
    public void addExperiment(Experiment exp) {
        expTable.insert(exp);
    }

    /**
     * findBird
     * finds a bird with a certain id
     * @param id the id of the bird to be found
     * @return either null (when bird is not found) or the Bird
     */
    public Bird findBird(String id)
    {
        return birdTable.findBird(id);
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
    }

    public ArrayList<Bird> searchBirds(String id, String name, String sex, String birthDate, String deathDate) {
        return searchBirds(new Bird(id, name, "", birthDate, deathDate, sex, ""));
    }

    public ArrayList<Bird> searchBirds(Bird inputBird) {
        return birdTable.searchBirds(inputBird);
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
            groupWithinExperiment, String startDate,String endDate) {
        return searchExperiments(new Experiment(studyTitle,studyType,groupWithinExperiment, startDate, endDate, "", "", "true"));
    }

    public ArrayList<Experiment> searchExperiments(Experiment experiment)
    {
        return expTable.searchBirds(experiment);
    }


    /**
     * getExperiment
     * get the experiment list
     * @return experimentList
     */
    public ArrayList<Experiment> getExperiment(){
        return expTable.getExperiment();
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
        return birdTable.getBird();
    }

}
