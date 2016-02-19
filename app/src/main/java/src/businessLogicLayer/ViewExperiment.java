package src.businessLogicLayer;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.javacrypt.se1.R;

import src.databaseLayer.DatabaseManager;
import src.databaseLayer.Experiment;

/**
 * Created by Zili on 2016-02-15.
 */
public class ViewExperiment extends ActionBarActivity{

    DatabaseManager db = new DatabaseManager();
    TextView viewStudyTitle;
    TextView viewStudyType;
    TextView viewGroupWithinExperiment;
    TextView viewStartDate;
    TextView viewEndDate;
    TextView viewExperimenters;
    TextView viewNotes;
    int i=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_experiment);
        viewStudyTitle = (TextView) findViewById(R.id.viewStudyTitle);
        viewStudyType = (TextView) findViewById(R.id.viewStudyType);
        viewGroupWithinExperiment = (TextView) findViewById(R.id.viewGroupWithinExperiment);
        viewStartDate = (TextView) findViewById(R.id.viewStartDate);
        viewEndDate = (TextView) findViewById(R.id.viewEndDate);
        viewExperimenters = (TextView) findViewById(R.id.viewExperimenters);
        viewNotes = (TextView) findViewById(R.id.viewNotes);


        viewStudyTitle.setText(db.getExperiment().get(i).getStudyTitle());
        viewStudyType.setText(db.getExperiment().get(i).getStudyType());
        viewGroupWithinExperiment.setText(db.getExperiment().get(i).getGroupWithinExperiment());
        viewStartDate.setText(db.getExperiment().get(i).getStartDate().toString());
        viewEndDate.setText(db.getExperiment().get(i).getEndDate().toString());
        viewExperimenters.setText(db.getExperiment().get(i).getExperimenters());
        viewNotes.setText(db.getExperiment().get(i).getNotes());

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_experiment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
