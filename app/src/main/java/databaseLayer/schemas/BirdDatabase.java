package databaseLayer.schemas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

import databaseLayer.DatabaseHelper;
import domainObjects.Bird;
import domainObjects.MedicalHistory;

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
    public static final String AND = " AND ";
    public static final String ISTHERE = " = ?";


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

    public ArrayList<Bird> getBird(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + BirdEntry.TABLE_LABEL;
        ArrayList<Bird> queryResult = new ArrayList<>();
        Bird addThisBird;
        Cursor c = db.rawQuery(query, null);

        if(c.getCount() > 0) {
            c.moveToFirst();
            do{
                addThisBird = new Bird(c.getString(1), c.getString(0), c.getString(3), c.getString(4), c.getString(5), c.getString(2), c.getString(6));
                addThisBird.setMedicalHistory(new MedicalHistory(c.getString(7)));
                queryResult.add(addThisBird);
            }while(c.moveToNext());
        }
        return queryResult;
    }

    public void removeBird(String id)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + BirdEntry.TABLE_LABEL + " WHERE " + BirdEntry.BIRD_ID + " = '" + id + "'");
    }
    /*
     *
     */
    public void insert(Bird input)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String stat = "";
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

        if(input.getBirthDate() != null)
        {
            inputVal.put(BirdEntry.BIRD_BDAY, String.valueOf(input.getBirthDate().getTimeInMillis()));
        }
        else
        {
            inputVal.put(BirdEntry.BIRD_BDAY, "");
        }

        if(input.getDeathDate() != null)
        {
            inputVal.put(BirdEntry.BIRD_DDAY, String.valueOf(input.getDeathDate().getTimeInMillis()));
        }
        else
        {
            inputVal.put(BirdEntry.BIRD_DDAY, "");
        }

        //Convert Boolean to String
        if(input.getStatus())
        {
            stat = "true";
        }
        else
        {
            stat = "false";
        }

        inputVal.put(BirdEntry.BIRD_STATUS, stat);

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

        db.close();
    }

    public Bird findBird(String id)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String [] row = BIRD_ROW;

        Cursor c = db.query(BirdEntry.TABLE_LABEL, row, BirdEntry.BIRD_NAME + "=?", new String[]{id}, null, null, null, null);

        if(c != null)
        {
            c.moveToFirst();
        }

        Bird result = new Bird(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6));

        db.close();

        return result;
    }

    private ArrayList<String> getWhereClause(Bird params)
    {
        String clause = "";
        ArrayList<String> clauseElements = new ArrayList<>();

        if(params.getId() != null && !params.getId().equals(""))
        {
            clauseElements.add(BirdEntry.BIRD_ID);
        }
        if(params.getName() != null && !params.getName().equals(""))
        {
            clauseElements.add(BirdEntry.BIRD_NAME);
        }

        if(params.getSex() != null && !params.getSex().equals(""))
        {
            clauseElements.add(BirdEntry.BIRD_SEX);
        }

        if(params.getExperiment() != null && !params.getExperiment().equals(""))
        {
            clauseElements.add(BirdEntry.BIRD_EXP);
        }

        if(params.getMedicalHistory() != null && !params.getMedicalHistory().toString().equals(""))
        {
            clauseElements.add(BirdEntry.BIRD_MED);
        }

        if(params.getBirthDate() != null)
        {
            clauseElements.add(BirdEntry.BIRD_BDAY);
        }

        if(params.getDeathDate() != null)
        {
            clauseElements.add(BirdEntry.BIRD_DDAY);
        }


        return clauseElements;
    }

    private String [] getSearchPara(Bird params)
    {
        ArrayList<String> tempArray = new ArrayList<>();

        if(params.getId() != null && !params.getId().equals(""))
        {
            tempArray.add(params.getId());
        }
        if(params.getName() != null && !params.getName().equals(""))
        {
            tempArray.add(params.getName());
        }

        if(params.getSex() != null && !params.getSex().equals(""))
        {
            tempArray.add(params.getSex());
        }

        if(params.getExperiment() != null && !params.getExperiment().equals(""))
        {
            tempArray.add(params.getExperiment());
        }

        if(params.getMedicalHistory() != null && !params.getMedicalHistory().toString().equals(""))
        {
            tempArray.add(params.getMedicalHistory().toString());
        }

        if(params.getBirthDate() != null)
        {
            tempArray.add(String.valueOf(params.getBirthDate().getTimeInMillis()));
        }

        if(params.getDeathDate() != null)
        {
            tempArray.add(String.valueOf(params.getDeathDate().getTimeInMillis()));
        }

       /* if(params.getStatus())
        {
            tempArray.add("true");
        }
        else
        {
            tempArray.add("false");
        }*/

        String [] returnArr = new String[tempArray.size()];
        returnArr = tempArray.toArray(returnArr);

        return returnArr;
    }

    public ArrayList<Bird> searchBirds(Bird params)
    {
        Bird addThisBird;
        ArrayList<Bird> queryResult = new ArrayList<>();
        String bDayString = "";
        String dDayString = "";
        String medical = "";
        String id = "";
        String sex = "";
        String name = "";
        String exp = "";
        String stat = "";
        params.setStatus(true);

        //Queries each data elem in params: BIRD_NAME =? AND BIRD_ID =? AND...
        ArrayList<String> whereClause  = getWhereClause(params);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String [] values = getSearchPara(params);
        String query = "SELECT * FROM " + BirdEntry.TABLE_LABEL + " WHERE ";

        for(int i = 0; i < whereClause.size()-1; i++)
        {
            query += whereClause.get(i) + " = " + "'" + values[i]+ "'" + AND;
        }
            query += whereClause.get(whereClause.size()-1) + " = " + "'" + values[values.length-1] + "'" ;

       String queryD = "SELECT * FROM " + BirdEntry.TABLE_LABEL + " WHERE " + whereClause.get(1) + " = " + "'" +values[1] + "'" + " AND " + whereClause.get(0) + " = '" + values[0] + "'";

        //Cursor c = db.rawQuery(query, values);

        Cursor c = db.rawQuery(query, null);
        c = db.rawQuery(queryD, null);
        if(c.getCount() > 0) {
            c.moveToFirst();
            do{
                name=c.getString(0);
                id= c.getString(1);
                sex=c.getString(2);
                exp=c.getString(3);
                bDayString=c.getString(4);
                dDayString=c.getString(5);
                stat=c.getString(6);
                addThisBird = new Bird(id, name, exp, bDayString, dDayString, sex, stat);
                addThisBird.setMedicalHistory(new MedicalHistory(c.getString(7)));
                queryResult.add(addThisBird);
            }while(c.moveToNext());
        }


        db.close();
        return queryResult;
    }
    public static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS " + BirdEntry.TABLE_LABEL+";";
    public static final String ADD_ENTRIES = "INSERT INTO " + BirdEntry.TABLE_LABEL+ " VALUES (";
    public static final String[] BIRD_ROW = {
            BirdEntry.BIRD_NAME,
            BirdEntry.BIRD_ID,
            BirdEntry.BIRD_SEX,
            BirdEntry.BIRD_EXP,
            BirdEntry.BIRD_BDAY,
            BirdEntry.BIRD_DDAY,
            BirdEntry.BIRD_STATUS,
            BirdEntry.BIRD_MED,
    };

}
