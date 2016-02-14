package net.javacrypt.se1;

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
        spEditor.putString("title",exp.txtStudyTitle);
        spEditor.putString("type",exp.txtStudyType);
        spEditor.putString("group",exp.txtGroupWithinExperiment);
        spEditor.putInt("startdate",exp.txtStartDate);
        spEditor.putInt("enddate",exp.txtEndDate);
        spEditor.putString("experimenters",exp.txtExperimenters);
        spEditor.putString("notes",exp.txtNotes);
        spEditor.commit();
    }

    public Experiment getCurrentExperiment()
    {
        String title = userLocalDatabase.getString("title", "");
        String type = userLocalDatabase.getString("type","");
        String group = userLocalDatabase.getString("group","");
        int startdate = userLocalDatabase.getInt("startdate",-1);
        int enddate = userLocalDatabase.getInt("enddate",-1);
        String experimenters =  userLocalDatabase.getString("experimenters","");
        String notes = userLocalDatabase.getString("notes","");

        Experiment storedExperiment = new Experiment(title,type,group,startdate,enddate,experimenters,notes);
        return storedExperiment;
    }

    public void setCurrentExperiment(boolean using)
    {
        SharedPreferences.Editor spEditor =userLocalDatabase.edit();
        spEditor.putBoolean("Using",using);
        spEditor.commit();
    }

    public void clearExperimentData()
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
