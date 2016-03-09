package databaseLayer;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.Objects;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by Kaj on 3/7/2016.
 */
public class DatabaseSQL {

    SQLiteDatabase birdData;
    public DatabaseSQL()
    {
        birdData = SQLiteDatabase.openOrCreateDatabase("BirdData", null);
        birdData.execSQL("CREATE TABLE IF NOT EXISTS BirdTable( ID VARCHAR, Name VARCHAR, BirthDate VARCHAR, DeathDate VARCHAR);");
        birdData.execSQL("CREATE TABLE IF NOT EXISTS ExperimentTable( Title VARCHAR, Type VARCHAR, Group VARCHAR, StartDate VARCHAR, EndDate VARCHAR, Experimenters VARCHAR, Notes VARCHAR);");
    }


}
