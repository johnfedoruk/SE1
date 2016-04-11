package businessLogicLayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.javacrypt.se1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import databaseLayer.DatabaseManager;
import domainObjects.Experiment;

/**
 * Created by Zili on 2016-02-15.
 */
@SuppressWarnings("all")
public class ViewExperiment extends AppCompatActivity implements View.OnClickListener {
    public final static String EXTRA_MESSAGE = "net.javacrypt.se1.MESSAGE";
     Experiment currentExperiment = null;

    TextView viewStudyTitle;
        TextView viewStudyType;
        TextView viewGroupWithinExperiment;
        TextView viewStartDate;
        TextView viewEndDate;
        TextView viewExperimenters;
        TextView viewNotes;

        Button btEditExperiment;


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
        Bundle bundle = intent.getExtras();
        this.currentExperiment = (Experiment)bundle.getSerializable("experiment");
        if (this.currentExperiment != null) {
            viewStudyTitle.setText(this.currentExperiment.getStudyTitle());
            viewStudyType.setText(this.currentExperiment.getStudyType());
            viewGroupWithinExperiment.setText(this.currentExperiment.getGroupWithinExperiment());
            viewStartDate.setText(this.currentExperiment.getDateString(this.currentExperiment.getStartDate()));
            viewEndDate.setText(this.currentExperiment.getDateString(this.currentExperiment.getEndDate()));
            viewExperimenters.setText(this.currentExperiment.getExperimenters());
            viewNotes.setText(this.currentExperiment.getNotes());
        }
        else {
            viewStudyTitle.setText("No Results");
        }

        btEditExperiment = (Button) findViewById(R.id.btEditExperiment);

        btEditExperiment.setOnClickListener(this);
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

        if (id == R.id.home) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }
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

        switch (v.getId()) {
            case R.id.btEditExperiment:

                if(this.currentExperiment!=null) {
                    Intent intent = new Intent(this,EditExperiment.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("experiment", this.currentExperiment);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }

    }

    public void endExperiment(View view)
    {
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to end this experiment?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        String experimentTitle = intent.getStringExtra(SearchBird.EXTRA_MESSAGE);
                        MainActivity.db.searchExperiments(experimentTitle,"","","","").get(0).setStatus(false);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}
