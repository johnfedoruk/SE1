package businessLogicLayer;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.javacrypt.se1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import databaseLayer.DatabaseManager;
import domainObjects.Experiment;

/**
 * Kevin
 */
public class EditExperiment extends AppCompatActivity implements View.OnClickListener {

    DatabaseManager db = MainActivity.db;
    Button btEditExperiment;
    public static Experiment currentExperiment = null;

    EditText txtStudyTitle, txtStudyType, txtGroupWithinExperiment, txtStartDate, txtEndDate, txtExperimenters, txtNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_experiment);
        btEditExperiment= (Button) findViewById(R.id.btEditExperiment);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.currentExperiment = (Experiment)bundle.getSerializable("experiment");

        txtStudyTitle = ((EditText) findViewById(R.id.txtStudyTitle));
        txtStudyType = ((EditText) findViewById(R.id.txtStudyType));
        txtGroupWithinExperiment = ((EditText) findViewById(R.id.txtGroupWithinExperiment));
        txtStartDate = ((EditText) findViewById(R.id.txtStartDate));
        txtEndDate = ((EditText) findViewById(R.id.txtEndDate));
        txtExperimenters = ((EditText) findViewById(R.id.txtExperimenters));
        txtNotes = ((EditText) findViewById(R.id.txtNotes));


        txtStudyTitle.setText(currentExperiment.getStudyTitle());
        txtStudyType.setText(currentExperiment.getStudyType());
        txtGroupWithinExperiment.setText(currentExperiment.getGroupWithinExperiment());
        txtStartDate.setText(currentExperiment.getDateString(currentExperiment.getStartDate()));
        txtEndDate.setText(currentExperiment.getDateString(currentExperiment.getEndDate()));
        txtExperimenters.setText(currentExperiment.getExperimenters());
        txtNotes.setText(currentExperiment.getNotes());


        btEditExperiment = (Button) findViewById(R.id.btEditExperiment);

        btEditExperiment.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_experiment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
                Intent myIntent = new Intent(EditExperiment.this, ExpAddSuccess.class);
                startActivity(myIntent);
                 break;

        }
    }

    @Override
    public void onStart() {
        super.onStart();


        EditText txtStartDate = (EditText) findViewById(R.id.txtStartDate);
        EditText txtEndDate = (EditText) findViewById(R.id.txtEndDate);
        txtStartDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
        txtEndDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });

    }



}
