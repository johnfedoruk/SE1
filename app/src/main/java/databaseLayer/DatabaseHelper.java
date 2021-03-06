package databaseLayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

import databaseLayer.schemas.BirdDatabase;
import databaseLayer.schemas.ExperimentDatabase;
import domainObjects.Experiment;

/**
 * Created by Kaj on 3/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 2;

    public DatabaseHelper(Context context)
    {
        super(context, BirdDatabase.BirdEntry.DB_NAME, null, DB_VERSION);

    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(BirdDatabase.CREATE_ENTRIES);
        db.execSQL(ExperimentDatabase.CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int currVersion, int newVersion)
    {
        db.execSQL(BirdDatabase.DELETE_ENTRIES);
        db.execSQL(ExperimentDatabase.DELETE_ENTRIES);
        onCreate(db);
    }
}
