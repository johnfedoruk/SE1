package databaseLayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import databaseLayer.schemas.BirdDatabase;

/**
 * Created by Kaj on 3/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "SQLDatabase.db";

    public DatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BirdDatabase.CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int currVersion, int newVersion)
    {
        db.execSQL(BirdDatabase.DELETE_ENTRIES);
        onCreate(db);
    }
}
