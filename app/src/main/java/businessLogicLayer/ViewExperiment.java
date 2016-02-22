package businessLogicLayer;

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
public class ViewExperiment extends AppCompatActivity implements View.OnClickListener {

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


        viewStudyTitle.setText(MainActivity.db.getExperiment().get(i).getStudyTitle());
        viewStudyType.setText(MainActivity.db.getExperiment().get(i).getStudyType());
        viewGroupWithinExperiment.setText(MainActivity.db.getExperiment().get(i).getGroupWithinExperiment());
        viewStartDate.setText(MainActivity.db.getExperiment().get(i).getDateString(MainActivity.db.getExperiment().get(i).getStartDate()));
        viewEndDate.setText(MainActivity.db.getExperiment().get(i).getDateString(MainActivity.db.getExperiment().get(i).getEndDate()));
        viewExperimenters.setText(MainActivity.db.getExperiment().get(i).getExperimenters());
        viewNotes.setText(MainActivity.db.getExperiment().get(i).getNotes());

        Button btNext = (Button) findViewById(R.id.btNext);
        btNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if((i>=0)&&(i<MainActivity.db.getExperiment().size()-1)) {
                    i = i + 1;

                    viewStudyTitle.setText(MainActivity.db.getExperiment().get(i).getStudyTitle());
                    viewStudyType.setText(MainActivity.db.getExperiment().get(i).getStudyType());
                    viewGroupWithinExperiment.setText(MainActivity.db.getExperiment().get(i).getGroupWithinExperiment());
                    viewStartDate.setText(MainActivity.db.getExperiment().get(i).getDateString(MainActivity.db.getExperiment().get(i).getStartDate()));
                    viewEndDate.setText(MainActivity.db.getExperiment().get(i).getDateString(MainActivity.db.getExperiment().get(i).getEndDate()));
                    viewExperimenters.setText(MainActivity.db.getExperiment().get(i).getExperimenters());
                    viewNotes.setText(MainActivity.db.getExperiment().get(i).getNotes());
                }
                else{}

            }
        });

        Button btPrevious = (Button) findViewById(R.id.btPrevious);
        btPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if((i>0)&&(i<(MainActivity.db.getExperiment().size()))) {
                    i=i-1;
                    viewStudyTitle.setText(MainActivity.db.getExperiment().get(i).getStudyTitle());
                    viewStudyType.setText(MainActivity.db.getExperiment().get(i).getStudyType());
                    viewGroupWithinExperiment.setText(MainActivity.db.getExperiment().get(i).getGroupWithinExperiment());
                    viewStartDate.setText(MainActivity.db.getExperiment().get(i).getDateString(MainActivity.db.getExperiment().get(i).getStartDate()));
                    viewEndDate.setText(MainActivity.db.getExperiment().get(i).getDateString(MainActivity.db.getExperiment().get(i).getEndDate()));
                    viewExperimenters.setText(MainActivity.db.getExperiment().get(i).getExperimenters());
                    viewNotes.setText(MainActivity.db.getExperiment().get(i).getNotes());
                }
                else{}

            }
        });


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

    @Override
    public void onClick(View v) {

    }
}
