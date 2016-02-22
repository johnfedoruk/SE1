package databaseLayer;

import android.content.Context;
import android.content.SharedPreferences;

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

    public void storeUserData(Experiment exp)
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("title",exp.getStudyTitle());
        spEditor.putString("type",exp.getStudyType());
        spEditor.putString("group",exp.getGroupWithinExperiment());
        spEditor.putString("startdate", exp.getStartDate().toString());
        spEditor.putString("enddate", exp.getEndDate().toString());
        spEditor.putString("experimenters",exp.getExperimenters());
        spEditor.putString("notes",exp.getNotes());
        spEditor.commit();
    }

    /*
    public Experiment getCurrentExperiment() {
        String title = userLocalDatabase.getString("title", "");
        String type = userLocalDatabase.getString("type", "");
        String group = userLocalDatabase.getString("group", "");
        Date startdate = userLocalDatabase.getString("startdate", "");
        Date enddate = userLocalDatabase.getString("enddate", "");
        String experimenters = userLocalDatabase.getString("experimenters", "");
        String notes = userLocalDatabase.getString("notes", "");

        Experiment storedExperiment = new Experiment(title, type, group, (Date) startdate, (Date) enddate, experimenters, notes);
        return storedExperiment;
    }*/

    public void setCurrentExperiment(boolean using)
    {
        SharedPreferences.Editor spEditor =userLocalDatabase.edit();
        spEditor.putBoolean("Using", using);
        spEditor.commit();
    }

    public void clearExperimentData()
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
