package databaseLayer.schemas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import databaseLayer.DatabaseHelper;
import domainObjects.Bird;

/**
 * Created by Kaj on 3/11/2016.
 */
public final class BirdDatabase {

    DatabaseHelper dbHelper;

    public BirdDatabase(Context context){dbHelper = new DatabaseHelper(context);}

    /*
     * Insert any property of a Bird into this class as a final String.
     */
    public static abstract class BirdEntry implements BaseColumns
    {
        public static final String TABLE_LABEL = "bird table";
        public static final String BIRD_NAME = "name";
        public static final String BIRD_ID = "id";
        public static final String BIRD_SEX = "sex";
        public static final String BIRD_EXP = "experiment";
        public static final String BIRD_BDAY = "birth date";
        public static final String BIRD_DDAY = "death date";
        public static final String BIRD_STATUS = "status";
        public static final String BIRD_MED = "medical history";
    }

    //Common SQL keywords
    public static final String TYPE_TEXT = " TEXT";
    public static final String COMMA = ",";
    public static final String KEY = "PRIMARY KEY";


    /*
     * Generates the table with the entries given in BirdEntry.
     * Possible improvement would be dynamically adding from the Hardcoded string list.
     */
    public static final String CREATE_ENTRIES = "CREATE TABLE " + BirdEntry.TABLE_LABEL + " (" +
            BirdEntry._ID + " INTEGER " + KEY + COMMA +
            BirdEntry.BIRD_NAME + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_ID + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_SEX + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_EXP + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_BDAY + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_DDAY + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_STATUS + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_MED + TYPE_TEXT +
            " )";

    public void insert(Bird input)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues inputVal = new ContentValues();
        inputVal.put(BirdEntry.BIRD_NAME, input.getName());
        inputVal.put(BirdEntry.BIRD_ID, input.getId());
        inputVal.put(BirdEntry.BIRD_SEX, input.getSex());
        inputVal.put(BirdEntry.BIRD_EXP, input.getExperiment());
        inputVal.put(BirdEntry.BIRD_BDAY, input.getBirthDate().getTime().toString());
        inputVal.put(BirdEntry.BIRD_DDAY, input.getDeathDate().getTime().toString());
        inputVal.put(BirdEntry.BIRD_STATUS, input.getStatus());
        inputVal.put(BirdEntry.BIRD_MED, input.getMedicalHistory().toString());

        long newRowId;
        newRowId = db.insert(BirdEntry.TABLE_LABEL, null, inputVal);

    }
    public static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS " + BirdEntry.TABLE_LABEL;
}
