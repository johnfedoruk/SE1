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
    
    private ArrayList<String> getWhereClause(Experiment params)
    {
        ArrayList<String> clauseElements = new ArrayList<>();

        if(params.getStudyTitle() != null && !params.getStudyTitle().equals(""))
        {
            clauseElements.add(ExpEntry.EXP_TITLE);
        }
        if(params.getStudyType() != null && !params.getStudyType().equals(""))
        {
            clauseElements.add(ExpEntry.EXP_TYPE);
        }

        if(params.getGroupWithinExperiment() != null && !params.getGroupWithinExperiment().equals(""))
        {
            clauseElements.add(ExpEntry.EXP_GROUP);
        }

        if(params.getStartDate() != null)
        {
            clauseElements.add(ExpEntry.EXP_START);
        }

        if(params.getEndDate() != null)
        {
            clauseElements.add(ExpEntry.EXP_END);
        }

        if(params.getExperimenters() != null && !params.getExperimenters().equals(""))
        {
            clauseElements.add(ExpEntry.EXP_TERS);
        }

        if(params.getNotes() != null && !params.getNotes().equals(""))
        {
            clauseElements.add(ExpEntry.EXP_NOTES);
        }

        clauseElements.add(ExpEntry.EXP_STAT);
        return clauseElements;
    }

    private String [] getSearchPara(Experiment params)
    {
        ArrayList<String> tempArray = new ArrayList<>();


        if(params.getStudyTitle() != null && !params.getStudyTitle().equals(""))
        {
            tempArray.add(params.getStudyTitle());
        }
        if(params.getStudyType() != null && !params.getStudyType().equals(""))
        {
            tempArray.add(params.getStudyType());
        }

        if(params.getGroupWithinExperiment() != null && !params.getGroupWithinExperiment().equals(""))
        {
            tempArray.add(params.getGroupWithinExperiment());
        }

        if(params.getStartDate() != null)
        {
            tempArray.add(String.valueOf(params.getStartDate().getTimeInMillis()));
        }

        if(params.getEndDate() != null)
        {
            tempArray.add(String.valueOf(params.getEndDate().getTimeInMillis()));
        }

        if(params.getExperimenters() != null && !params.getExperimenters().equals(""))
        {
            tempArray.add(params.getExperimenters());
        }

        if(params.getNotes() != null && !params.getNotes().equals(""))
        {
            tempArray.add(params.getNotes());
        }

        if(params.getStatus())
        {
            tempArray.add("true");
        }
        else
        {
            tempArray.add("false");
        }

        String [] returnArr = new String[tempArray.size()];
        returnArr = tempArray.toArray(returnArr);

        return returnArr;
    }

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
        queryResult.trimToSize();
        return queryResult;

        /*
        String id = (inputBird.getId()!=null)?(inputBird.getId().trim().toLowerCase()):(null);
        String name = (inputBird.getName()!=null)?(inputBird.getName().trim().toLowerCase()):(null);
        String sex = (inputBird.getSex()!=null)?(inputBird.getSex().trim().toLowerCase()):(null);
        String birthDate = (inputBird.getBirthDate()!=null)?(inputBird.getDateString(inputBird.getBirthDate())):(null);
        String deathDate = (inputBird.getDeathDate()!=null)?(inputBird.getDateString(inputBird.getDeathDate())):(null);

        for(int i=queryResult.size()-1;i>=0;i--) {
            if(id!=null&&id.length()>0&& queryResult.get(i).getId() != null &&
                    !queryResult.get(i).getId().trim().toLowerCase().equals(id)) {
                queryResult.remove(i);
                continue;
            }
            if(name!=null&&name.length()>0&& queryResult.get(i).getName() != null &&
                    !queryResult.get(i).getName().trim().toLowerCase().equals(name)) {
                queryResult.remove(i);
                continue;
            }
            if(sex!=null&&sex.length()>0&& queryResult.get(i).getSex() != null &&
                    !queryResult.get(i).getSex().trim().toLowerCase().equals(sex)) {
                queryResult.remove(i);
                continue;
            }

            if((birthDate!=null&&queryResult.get(i).getBirthDate()==null)||(birthDate!=null&&
                    !queryResult.get(i).getDateString(queryResult.get(i).getBirthDate())
                            .equals(birthDate))) {
                queryResult.remove(i);
                continue;
            }
            if((deathDate!=null&&queryResult.get(i).getDeathDate()==null)||(deathDate!=null&&
                    !queryResult.get(i).getDateString(queryResult.get(i).getDeathDate())
                            .equals(deathDate))) {
                queryResult.remove(i);
                continue;
            }
        }
        queryResult.trimToSize();
        return queryResult;
        */
















        /*
        Experiment addThisExp;
        ArrayList<Experiment> queryResult = new ArrayList<>();
        String title = "";
        String type = "";
        String group = "";
        String start = "";
        String end = "";
        String experts = "";
        String notes = "";
        String stat = "";
        params.setStatus(true);

        //Queries each data elem in params: BIRD_NAME =? AND BIRD_ID =? AND...
        ArrayList<String> whereClause  = getWhereClause(params);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String [] values = getSearchPara(params);
        String query = "SELECT * FROM " + ExpEntry.TABLE_LABEL + " WHERE ";

        for(int i = 0; i < whereClause.size()-1; i++)
        {
            query += whereClause.get(i) + " = " + "'" + values[i]+ "'" + AND;
        }
        query += whereClause.get(whereClause.size()-1) + " = " + "'" + values[values.length-1] + "'" ;

        String queryD = "SELECT * FROM " + ExpEntry.TABLE_LABEL + " WHERE " + whereClause.get(0) + " = " + "'" +values[0] + "'" + " AND " + whereClause.get(1) + " = '" + values[1] + "'";

        //Cursor c = db.rawQuery(query, values);

        Cursor c = db.rawQuery(query, null);
        c = db.rawQuery(queryD, null);
        if(c.getCount() > 0) {
            c.moveToFirst();
            do{
                title=c.getString(0);
                type= c.getString(1);
                group=c.getString(2);
                start=c.getString(3);
                end=c.getString(4);
                experts=c.getString(5);
                notes=c.getString(6);
                stat=c.getString(7);
                addThisExp = new Experiment(title, type, group, start, end, experts, notes, stat);
                queryResult.add(addThisExp);
            }while(c.moveToNext());
        }


        db.close();
        return queryResult;
        */
    }
    
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
