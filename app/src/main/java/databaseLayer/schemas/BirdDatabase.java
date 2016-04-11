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
        public static final String BIRD_DAD = "bird_dad";
        public static final String BIRD_MOM = "bird_mom";
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
            BirdEntry.BIRD_MED + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_DAD + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_MOM + TYPE_TEXT +
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
                addThisBird = new Bird(c.getString(1), c.getString(0), c.getString(3), c.getString(4), c.getString(5), c.getString(2), c.getString(6), c.getString(7), c.getString(8));
                addThisBird.setMedicalHistory(new MedicalHistory(c.getString(7)));
                queryResult.add(addThisBird);
            }while(c.moveToNext());
        }
        return queryResult;
    }

    public void removeBird(String id)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + BirdEntry.TABLE_LABEL + " WHERE " + BirdEntry.BIRD_ID + " = " + "'" + id + "'");
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
            inputVal.put(BirdEntry.BIRD_BDAY, input.getDateString(input.getBirthDate(), "yyyy-MM-dd"));
        }
        else
        {
            inputVal.put(BirdEntry.BIRD_BDAY, "");
        }

        if(input.getDeathDate() != null)
        {
            inputVal.put(BirdEntry.BIRD_DDAY, input.getDateString(input.getDeathDate(),"yyyy-MM-dd"));
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

        if(params.getBirthDate() != null && !params.getBirthDate().toString().equals(""))
        {
            clauseElements.add(BirdEntry.BIRD_BDAY);
        }

        if(params.getDeathDate() != null && !params.getDeathDate().toString().equals(""))
        {
            clauseElements.add(BirdEntry.BIRD_DDAY);
        }

        if(params.getDad() != null && !params.getDad().toString().equals(""))
        {
            clauseElements.add(BirdEntry.BIRD_DAD);
        }

        if(params.getMom() != null && !params.getDad().toString().equals(""))
        {
            clauseElements.add(BirdEntry.BIRD_MOM);
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

        if(params.getDad() != null)
        {
            tempArray.add(String.valueOf(params.getDad()));
        }

        if (params.getMom() != null)
        {
            tempArray.add(String.valueOf(params.getMom()));
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

    public ArrayList<Bird> searchBirds(Bird inputBird)
    {
        ArrayList<Bird> queryResult = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + BirdEntry.TABLE_LABEL + ";";
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String name;
                String id;
                String sex;
                String exp;
                String bDayString;
                String dDayString;
                String stat;
                Bird addThisBird;
                name = c.getString(0);
                id = c.getString(1);
                sex = c.getString(2);
                exp = c.getString(3);
                bDayString = c.getString(4);
                dDayString = c.getString(5);
                stat = c.getString(6);
                addThisBird = new Bird(id, name, exp, bDayString, dDayString, sex, stat);
                addThisBird.setMedicalHistory(new MedicalHistory(c.getString(7)));
                queryResult.add(addThisBird);
            } while (c.moveToNext());
        }
        db.close();
        String id = (inputBird.getId()!=null)?(inputBird.getId().trim().toLowerCase()):(null);
        String name = (inputBird.getName()!=null)?(inputBird.getName().trim().toLowerCase()):(null);
        String sex = (inputBird.getSex()!=null)?(inputBird.getSex().trim().toLowerCase()):(null);
        String birthDate = (inputBird.getBirthDate()!=null)?(inputBird.getDateString(inputBird.getBirthDate())):(null);
        String deathDate = (inputBird.getDeathDate()!=null)?(inputBird.getDateString(inputBird.getDeathDate())):(null);

        /**This will be replaced with a simple sql statement**/
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
    }
    public static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS " + BirdEntry.TABLE_LABEL+";";
    public static final String ADD_ENTRIES = "INSERT INTO " + BirdEntry.TABLE_LABEL + " VALUES (";
    public static final String[] BIRD_ROW = {
            BirdEntry.BIRD_NAME,
            BirdEntry.BIRD_ID,
            BirdEntry.BIRD_SEX,
            BirdEntry.BIRD_EXP,
            BirdEntry.BIRD_BDAY,
            BirdEntry.BIRD_DDAY,
            BirdEntry.BIRD_STATUS,
            BirdEntry.BIRD_MED,
            BirdEntry.BIRD_DAD,
            BirdEntry.BIRD_MOM
    };

}
