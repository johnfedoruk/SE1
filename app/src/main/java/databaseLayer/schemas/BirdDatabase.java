package databaseLayer.schemas;

import android.provider.BaseColumns;

/**
 * Created by Kaj on 3/11/2016.
 */
public final class BirdDatabase {

    public BirdDatabase(){}

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

    public static final String TYPE_TEXT = " TEXT";
    public static final String COMMA = ",";
    public static final String CREATE_ENTRIES = "CREATE TABLE " + BirdEntry.TABLE_LABEL + " (" +
            BirdEntry._ID + " INTEGER PRIMARY KEY," +
            BirdEntry.BIRD_NAME + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_ID + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_SEX + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_EXP + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_BDAY + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_DDAY + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_STATUS + TYPE_TEXT + COMMA +
            BirdEntry.BIRD_MED + TYPE_TEXT +
            " )";

    public static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS " + BirdEntry.TABLE_LABEL;
}
