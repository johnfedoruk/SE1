package databaseLayer;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;

import databaseLayer.schemas.BirdDatabase;
import domainObjects.Bird;
import domainObjects.Experiment;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by Kaj on 3/7/2016.
 */
public class DatabaseSQL{

    DatabaseHelper dbHelper;
    BirdDatabase birdTable;

    public DatabaseSQL(Context context)
    {
        dbHelper = new DatabaseHelper(context);
        birdTable = new BirdDatabase(context);
    }


    public void clearDatabases()
    {
        SQLiteDatabase dirtyDB = dbHelper.getWritableDatabase();
        dirtyDB.execSQL(BirdDatabase.DELETE_ENTRIES);
    }

    public void insert(Bird values)
    {
        birdTable.insert(values);
    }



}
