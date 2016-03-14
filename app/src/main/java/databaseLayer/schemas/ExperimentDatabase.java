package databaseLayer.schemas;

import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

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
        public static final String DB_NAME = "exp_data";
    }

    //Common SQL keywords
    public static final String TYPE_TEXT = " TEXT";
    public static final String COMMA = ", ";
    public static final String KEY = "PRIMARY KEY AUTOINCREMENT";
    public static final String AND = " AND ";
    public static final String ISTHERE = " = ?";

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


}
