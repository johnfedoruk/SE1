package databaseLayer;

import android.content.Context;
import android.content.SharedPreferences;

import domainObjects.Experiment;

/**
 * Created by Jose Matundan on 2/14/2016.
 */
public class ExperimentLocalStore {

    public static final String SP_NAME = "experimentDetails";
    SharedPreferences userLocalDatabase;

    public ExperimentLocalStore(Context context)
    {
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }
}
