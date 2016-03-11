package databaseLayer;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import databaseLayer.schemas.BirdDatabase;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by Kaj on 3/7/2016.
 */
public class DatabaseSQL extends SQLiteOpenHelper{

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "SQLDatabase.db";

    SQLiteDatabase birdData;
    public DatabaseSQL(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(BirdDatabase.CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int currVersion, int newVersion)
    {
        db.execSQL(BirdDatabase.DELETE_ENTRIES);
        onCreate(db);
    }

}
