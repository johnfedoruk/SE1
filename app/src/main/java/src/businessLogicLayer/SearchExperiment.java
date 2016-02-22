package src.businessLogicLayer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.javacrypt.se1.R;


public class SearchExperiment extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "net.javacrypt.se1.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_experiment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_experiment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Allows the user to open an experiment
     * @param view
     */
    public void openViewExperiment(View view) {
        Intent intent = new Intent(this,ViewExperiments.class);
        String StudyTitle = "";
        String StudyType = "";
        String GroupWithinExperiment = "";
        String StartDate = "";
        String EndDate = "";
        String[] params = {StudyTitle,StudyType,
                GroupWithinExperiment,StartDate,EndDate};
        intent.putExtra(EXTRA_MESSAGE,params);
        startActivity(intent);
    }
}
