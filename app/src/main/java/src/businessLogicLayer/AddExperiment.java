package src.businessLogicLayer;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import net.javacrypt.se1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import src.databaseLayer.DatabaseManager;
import src.databaseLayer.Experiment;

/*============================JOSE============================*/
public class AddExperiment extends ActionBarActivity implements View.OnClickListener {

    DatabaseManager db = new DatabaseManager();
    Button btCreateExperiment;

    EditText txtStudyTitle, txtStudyType, txtGroupWithinExperiment, txtStartDate, txtEndDate, txtExperimenters, txtNotes;
    src.databaseLayer.ExperimentLocalStore ExperimentLocalStore;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_experiment);
        Button btAddBird = (Button) findViewById(R.id.btCreateExperiment);
        btAddBird.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(AddExperiment.this);
                progressDialog.setTitle("Adding Bird");
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
                Intent myIntent = new Intent(AddExperiment.this, ExpAddSuccess.class);
                startActivity(myIntent);

            }
        });

        txtStudyTitle = ((EditText) findViewById(R.id.txtStudyTitle));
        txtStudyType = ((EditText) findViewById(R.id.txtStudyType));
        txtGroupWithinExperiment = ((EditText) findViewById(R.id.txtGroupWithinExperiment));
        txtStartDate = ((EditText) findViewById(R.id.txtStartDate));
        txtEndDate = ((EditText) findViewById(R.id.txtEndDate));
        txtExperimenters = ((EditText) findViewById(R.id.txtExperimenters));
        txtNotes = ((EditText) findViewById(R.id.txtNotes));

        btCreateExperiment = (Button) findViewById(R.id.btCreateExperiment);

        btCreateExperiment.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btCreateExperiment:

                String title = txtStudyTitle.getText().toString();
                String type = txtStudyType.getText().toString();
                String group = txtGroupWithinExperiment.getText().toString();
                Date startdate = null;
                Date enddate = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    startdate = ((Date)sdf.parse(txtStartDate.getText().toString()));

                    enddate = ((Date)(sdf.parse(txtEndDate.getText().toString())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String experimenters =  txtExperimenters.getText().toString();
                String notes = txtNotes.getText().toString();

                Experiment exp = new Experiment(title,type,group,startdate,enddate,experimenters,notes);
                db.addExperiment(exp);
                startActivity(new Intent(this, ExpAddSuccess.class));
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddExperiment Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://net.javacrypt.se1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);

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

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddExperiment Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://net.javacrypt.se1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}
/*============================JOSE============================*/
