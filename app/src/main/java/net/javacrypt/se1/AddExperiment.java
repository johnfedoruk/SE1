package net.javacrypt.se1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
/*============================JOSE============================*/
public class AddExperiment extends ActionBarActivity implements View.OnClickListener {

    Button btCreateExperiment;
    EditText txtStudyTitle, txtStudyType, txtGroupWithinExperiment, txtStartDate, txtEndDate, txtExperimenters, txtNotes;
    ExperimentLocalStore ExperimentLocalStore;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_experiment);


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
                int startdate = Integer.parseInt(txtStartDate.getText().toString());
                int enddate = Integer.parseInt(txtEndDate.getText().toString());
                String experimenters =  txtExperimenters.getText().toString();
                String notes = txtNotes.getText().toString();

                Experiment exp = new Experiment(title,type,group,startdate,enddate,experimenters,notes);
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
