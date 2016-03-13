package databaseLayer.schemas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import databaseLayer.DatabaseHelper;
import domainObjects.Bird;

/**
 * Created by Kaj on 3/11/2016.
 */
public final class BirdDatabase {

    SQLiteOpenHelper dbHelper;
    public BirdDatabase(SQLiteOpenHelper bringMeMoreMead){
        dbHelper = bringMeMoreMead;
    }

    /*
     * Insert any property of a Bird into this class as a final String.
     */
    public static abstract class BirdEntry implements BaseColumns
    {
        public static final String TABLE_LABEL = "bird_table";
        public static final String BIRD_NAME = "name";
        public static final String BIRD_ID = "id";
        public static final String BIRD_SEX = "sex";
        public static final String BIRD_EXP = "experiment";
        public static final String BIRD_BDAY = "birth_date";
        public static final String BIRD_DDAY = "death_date";
        public static final String BIRD_STATUS = "status";
        public static final String BIRD_MED = "medical_history";
        public static final String DB_NAME = "bird_data";
    }

    //Common SQL keywords
    public static final String TYPE_TEXT = " TEXT";
    public static final String COMMA = ", ";
    public static final String KEY = "PRIMARY KEY AUTOINCREMENT";


    /*
     * Generates the table with the entries given in BirdEntry.
     * Possible improvement would be dynamically adding from the Hardcoded string list.
     */
    public static final String CREATE_ENTRIES = "CREATE TABLE " + BirdEntry.TABLE_LABEL + " (" +
            BirdEntry.BIRD_NAME + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_ID + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_SEX + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_EXP + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_BDAY + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_DDAY + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_STATUS + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_MED + TYPE_TEXT +
            " );";

    public void insert(Bird input)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues inputVal = new ContentValues();
        if(input.getName() != null && !input.getName().equals(""))
        {
            inputVal.put(BirdEntry.BIRD_NAME, input.getName());
        }
        else
        {
            inputVal.put(BirdEntry.BIRD_NAME, "");
        }

        if(input.getId() != null && !input.getId().equals(""))
        {
            inputVal.put(BirdEntry.BIRD_ID, input.getId());
        }
        else
        {
            inputVal.put(BirdEntry.BIRD_ID, "");
        }

        if(input.getSex() != null && !input.getSex().equals(""))
        {
            inputVal.put(BirdEntry.BIRD_SEX, input.getSex());
        }
        else
        {
            inputVal.put(BirdEntry.BIRD_SEX, "");
        }

        if(input.getExperiment() != null && !input.getExperiment().equals(""))
        {
            inputVal.put(BirdEntry.BIRD_EXP, input.getExperiment());
        }
        else
        {
            inputVal.put(BirdEntry.BIRD_EXP, "");
        }

        if(input.getBirthDate() != null && !input.getBirthDate().getTime().toString().equals(""))
        {
            inputVal.put(BirdEntry.BIRD_BDAY, input.getBirthDate().getTime().toString());
        }
        else
        {
            inputVal.put(BirdEntry.BIRD_BDAY, "");
        }

        if(input.getDeathDate() != null && !input.getDeathDate().getTime().toString().equals(""))
        {
            inputVal.put(BirdEntry.BIRD_DDAY, input.getDeathDate().getTime().toString());
        }
        else
        {
            inputVal.put(BirdEntry.BIRD_DDAY, "");
        }

        inputVal.put(BirdEntry.BIRD_STATUS, input.getStatus());

        if(input.getMedicalHistory() != null && !(input.getMedicalHistory().toString().equals("")))
        {
            inputVal.put(BirdEntry.BIRD_MED, input.getMedicalHistory().toString());
        }
        else
        {
            inputVal.put(BirdEntry.BIRD_MED, "");
        }

        long newRowId;
        newRowId = db.insert(BirdEntry.TABLE_LABEL, null, inputVal);

    }

    public Bird findBird(String id)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String [] row = {
                BirdEntry.BIRD_NAME,
                BirdEntry.BIRD_ID,
                BirdEntry.BIRD_SEX,
                BirdEntry.BIRD_EXP,
                BirdEntry.BIRD_BDAY,
                BirdEntry.BIRD_DDAY,
                BirdEntry.BIRD_STATUS,
                BirdEntry.BIRD_MED,
        };

        Cursor c = db.query(BirdEntry.TABLE_LABEL, row, BirdEntry.BIRD_NAME + "=?", new String[]{id}, null, null, null, null);

        if(c != null)
        {
            c.moveToFirst();
        }

        Bird result = new Bird(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6));

        return result;
    }

    public static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS " + BirdEntry.TABLE_LABEL;
}
