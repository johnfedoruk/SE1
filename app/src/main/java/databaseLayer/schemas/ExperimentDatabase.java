package databaseLayer.schemas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

import domainObjects.Experiment;

/**
 * Created by Kaj on 3/14/2016.
 */
public class ExperimentDatabase {

    SQLiteOpenHelper dbHelper;
    public ExperimentDatabase(SQLiteOpenHelper bringMeMoreMead){
        dbHelper = bringMeMoreMead;
    }

    public static abstract class ExpEntry implements BaseColumns
    {
        public static final String TABLE_LABEL = "exp_table";
        public static final String EXP_TITLE = "title";
        public static final String EXP_TYPE = "type";
        public static final String EXP_GROUP = "group";
        public static final String EXP_START = "start_date";
        public static final String EXP_END = "end_date";
        public static final String EXP_TERS = "experimenters";
        public static final String EXP_NOTES = "notes";
        public static final String EXP_STAT = "status";
    }

    //Common SQL keywords
    public static final String TYPE_TEXT = " TEXT";
    public static final String COMMA = ", ";
    public static final String KEY = "PRIMARY KEY AUTOINCREMENT";
    public static final String AND = " AND ";
    public static final String ISTHERE = " = ?";

    public static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ExpEntry.TABLE_LABEL+";";

    public static final String CREATE_ENTRIES = "CREATE TABLE " + ExpEntry.TABLE_LABEL + " (" +
            ExpEntry.EXP_TITLE + TYPE_TEXT + COMMA +
            ExpEntry.EXP_TYPE + TYPE_TEXT + COMMA +
            ExpEntry.EXP_GROUP + TYPE_TEXT + COMMA +
            ExpEntry.EXP_START + TYPE_TEXT + COMMA +
            ExpEntry.EXP_END + TYPE_TEXT + COMMA +
            ExpEntry.EXP_TERS + TYPE_TEXT + COMMA +
            ExpEntry.EXP_NOTES + TYPE_TEXT + COMMA +
            ExpEntry.EXP_STAT + TYPE_TEXT +
            " );";
    
    public void insert(Experiment input) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String stat = "";   
        ContentValues inputVal = new ContentValues();
        if (input.getStudyTitle() != null && !input.getStudyTitle().equals("")) {
            inputVal.put(ExpEntry.EXP_TITLE, input.getStudyTitle());
        } else {
            inputVal.put(ExpEntry.EXP_TITLE, "");
        }

        if (input.getStudyType() != null && !input.getStudyType().equals("")) {
            inputVal.put(ExpEntry.EXP_TYPE, input.getStudyType());
        } else {
            inputVal.put(ExpEntry.EXP_TYPE, "");
        }

        if (input.getGroupWithinExperiment() != null && !input.getGroupWithinExperiment().equals("")) {
            inputVal.put(ExpEntry.EXP_GROUP, input.getGroupWithinExperiment());
        } else {
            inputVal.put(ExpEntry.EXP_GROUP, "");
        }

        if (input.getStartDate() != null) {
            inputVal.put(ExpEntry.EXP_START, String.valueOf(input.getStartDate().getTimeInMillis()));
        } else {
             inputVal.put(ExpEntry.EXP_START, "");
        }

        if (input.getEndDate() != null) {
            inputVal.put(ExpEntry.EXP_END, String.valueOf(input.getEndDate().getTimeInMillis()));
        } else {
            inputVal.put(ExpEntry.EXP_END, "");
         }

        if (input.getStatus()) {
            inputVal.put(ExpEntry.EXP_STAT, "true");
        } else {
            inputVal.put(ExpEntry.EXP_STAT, "false");
        }

    long newRowId;
    newRowId = db.insert(ExpEntry.TABLE_LABEL, null, inputVal);

    db.close();

    }

    public ArrayList<Experiment> getExperiment()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + ExpEntry.TABLE_LABEL;
        ArrayList<Experiment> queryResult = new ArrayList<>();
        Experiment addThisExp;
        Cursor c = db.rawQuery(query, null);

        if(c.getCount() > 0) {
            c.moveToFirst();
            do{
                addThisExp = new Experiment(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7));
                queryResult.add(addThisExp);
            }while(c.moveToNext());
        }
        return queryResult;

    }
}
