package databaseLayer.schemas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Calendar;

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
        public static final String EXP_GROUP = "study";
        public static final String EXP_START = "start_date";
        public static final String EXP_END = "end_date";
        public static final String EXP_TERS = "experimenters";
        public static final String EXP_NOTES = "notes";
        public static final String EXP_STAT = "status";
    }

    //Common SQL keywords
    public static final String TYPE_TEXT = " TEXT";
    public static final String COMMA = ", ";

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


    public ArrayList<Experiment> searchExperiments(Experiment params)
    {
        ArrayList<Experiment> queryResult = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + ExpEntry.TABLE_LABEL + ";";
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String title=c.getString(0);
                String type= c.getString(1);
                String group=c.getString(2);
                String start=c.getString(3);
                String end=c.getString(4);
                String experts=c.getString(5);
                String notes=c.getString(6);
                String stat=c.getString(7);
                queryResult.add(new Experiment(title, type, group, start, end, experts, notes, stat));
            } while (c.moveToNext());
        }
        db.close();
        String title = (params.getStudyTitle()!=null)?(params.getStudyTitle().trim().toLowerCase()):(null);
        String type = (params.getStudyType()!=null)?(params.getStudyType().trim().toLowerCase()):(null);
        String group = (params.getGroupWithinExperiment()!=null)?(params.getGroupWithinExperiment().trim().toLowerCase()):(null);
        String start = (params.getStartDate()!=null)?(params.getDateString(params.getStartDate())):(null);
        String end = (params.getEndDate()!=null)?(params.getDateString(params.getEndDate())):(null);
        if(queryResult==null||queryResult.size()<1)
            return null;
        for(int i=queryResult.size()-1;i>=0;i--) {
            if(title!=null&&title.length()>0&& queryResult.get(i).getStudyTitle() != null &&
                    !queryResult.get(i).getStudyTitle().trim().toLowerCase().equals(title)) {
                queryResult.remove(i);
                continue;
            }
            if(type!=null&&type.length()>0&& queryResult.get(i).getStudyType() != null &&
                    !queryResult.get(i).getStudyType().trim().toLowerCase().equals(type)) {
                queryResult.remove(i);
                continue;
            }
            if(group !=null&&group.length()>0&& queryResult.get(i).getGroupWithinExperiment() != null &&
                    !queryResult.get(i).getGroupWithinExperiment().trim().toLowerCase().equals(group)) {
                queryResult.remove(i);
                continue;
            }

            if((start!= null&&queryResult.get(i).getStartDate()==null)||(start!=null&&
                    !queryResult.get(i).getDateString(queryResult.get(i).getStartDate())
                            .equals(start))) {
                queryResult.remove(i);
                continue;
            }
            if((end!=null&&queryResult.get(i).getEndDate()==null)||(end!=null&&
                    !queryResult.get(i).getDateString(queryResult.get(i).getEndDate())
                            .equals(end))) {
                queryResult.remove(i);
                continue;
            }
        }

        queryResult.trimToSize();
        return queryResult;
    }
    
    public void insert(Experiment input) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

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

        if(input.getStartDate() != null)
        {
            inputVal.put(ExpEntry.EXP_START, input.getDateString(input.getStartDate()));
        }
        else
        {
            inputVal.put(ExpEntry.EXP_START, "");
        }

        if(input.getEndDate() != null)
        {
            inputVal.put(ExpEntry.EXP_END, input.getDateString(input.getEndDate()));
        }
        else
        {
            inputVal.put(ExpEntry.EXP_END, "");
        }

        if (input.getExperimenters() != null && !input.getExperimenters().equals("")) {
            inputVal.put(ExpEntry.EXP_TERS, input.getExperimenters());
        } else {
            inputVal.put(ExpEntry.EXP_TERS, "");
        }

        if (input.getNotes() != null && !input.getNotes().equals("")) {
            inputVal.put(ExpEntry.EXP_NOTES, input.getNotes());
        } else {
            inputVal.put(ExpEntry.EXP_NOTES, "");
        }

        if (input.getStatus()) {
            inputVal.put(ExpEntry.EXP_STAT, "true");
        } else {
            inputVal.put(ExpEntry.EXP_STAT, "false");
        }

        db.insert(ExpEntry.TABLE_LABEL, null, inputVal);

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

    public void removeExperiment(Experiment experiment) {
        if(experiment==null)
            return;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(
                "DELETE FROM "
                + ExpEntry.TABLE_LABEL
                + " WHERE "
                + ExpEntry.EXP_TITLE
                + " = "
                + "'"
                + experiment.getStudyTitle()
                + "'"
                + " AND "
                + ExpEntry.EXP_TYPE
                + " = "
                + "'"
                + experiment.getStudyType()
                + "'"
        );
    }
}
