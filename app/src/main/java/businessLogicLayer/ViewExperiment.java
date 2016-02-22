package businessLogicLayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.javacrypt.se1.R;

/**
 * Created by Zili on 2016-02-15.
 */
@SuppressWarnings("all")
public class ViewExperiment extends AppCompatActivity implements View.OnClickListener {

    TextView viewStudyTitle;
    TextView viewStudyType;
    TextView viewGroupWithinExperiment;
    TextView viewStartDate;
    TextView viewEndDate;
    TextView viewExperimenters;
    TextView viewNotes;
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

        Intent intent = getIntent();
        String experimentTitle = intent.getStringExtra(SearchBird.EXTRA_MESSAGE);

        if(experimentTitle.length()>0) {
            viewStudyTitle.setText(MainActivity.db.searchExperiments(experimentTitle,"","","","").get(0).getStudyTitle());
            viewStudyType.setText(MainActivity.db.searchExperiments(experimentTitle,"","","","").get(0).getStudyType());
            viewGroupWithinExperiment.setText(MainActivity.db.searchExperiments(experimentTitle,"","","","").get(0).getGroupWithinExperiment());
            viewStartDate.setText(MainActivity.db.searchExperiments(experimentTitle,"","","","").get(0).getDateString(MainActivity.db.getExperiment().get(0).getStartDate()));
            viewEndDate.setText(MainActivity.db.searchExperiments(experimentTitle,"","","","").get(0).getDateString(MainActivity.db.getExperiment().get(0).getEndDate()));
            viewExperimenters.setText(MainActivity.db.searchExperiments(experimentTitle,"","","","").get(0).getExperimenters());
            viewNotes.setText(MainActivity.db.searchExperiments(experimentTitle,"","","","").get(0).getNotes());
        }
        else {
            viewStudyTitle.setText("No Results");
        }
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

        if (id == R.id.add_bird) {
            Intent intent = new Intent(this,AddBird.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.add_experiment) {
            Intent intent = new Intent(this,AddExperiment.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.search_bird) {
            Intent intent = new Intent(this,SearchBird.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.search_experiment) {
            Intent intent = new Intent(this,SearchExperiment.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
