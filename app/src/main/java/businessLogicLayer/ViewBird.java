package businessLogicLayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.javacrypt.se1.R;


import java.util.ArrayList;

import databaseLayer.DatabaseManager;
import domainObjects.Bird;
import domainObjects.MedicalHistory;

/*
 * Created by Kaj Moroz
 *
 */
public class ViewBird extends AppCompatActivity {

    static int id = 1;
    private Bird currentBird = null;

    @Override
    /*
     * When onCreate is called, it requires a Bird's ID tag to be passed into ViewBird.
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bird);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.currentBird = (Bird)bundle.getSerializable("bird");

        /**
         * Append Bird's Info to xml elements
         */
        if(currentBird != null && currentBird.getId() != null)
        {

            if (currentBird != null)
            {
                TextView curr = (TextView) findViewById(R.id.birdName);
                curr.append(currentBird.getName());

                curr = (TextView) findViewById(R.id.birdID);
                curr.append(currentBird.getId());

                curr = (TextView) findViewById(R.id.birdBirthdate);
                curr.append(currentBird.getDateString(currentBird.getBirthDate()));

                curr = (TextView) findViewById(R.id.birdDeathdate);
                curr.append(currentBird.getDateString(currentBird.getDeathDate()));

                curr = (TextView) findViewById(R.id.birdSex);
                curr.append(currentBird.getSex());

                MedicalHistory medicalHistory = currentBird.getMedicalHistory();
                if(medicalHistory!=null&&medicalHistory.getHealthIssue().length()>0) {
                    RelativeLayout layout = (RelativeLayout)findViewById(R.id.medHistoryWrapper);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT );
                    /**
                     * Medial History Title
                     */
                    int currId = newId();
                    curr = new TextView(this);
                    curr.setId(currId);
                    curr.setTypeface(null, Typeface.BOLD);
                    curr.setText("Medical History");
                    curr.setTextSize(20);
                    params.addRule(RelativeLayout.BELOW, R.id.editBird);
                    params.setMargins(0,20,0,0);
                    layout.addView(curr, params);
                    /**
                     * Date of report
                     */
                    curr = new TextView(this);
                    curr.setText("Date of report: " +
                            medicalHistory.getDateString(medicalHistory.getDateOfReport()));
                    params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT );
                    params.addRule(RelativeLayout.BELOW, currId);
                    currId = newId();
                    curr.setId(currId);
                    layout.addView(curr, params);
                    /**
                     * Health Issues
                     */
                    curr = new TextView(this);
                    curr.setText("Health Issues: " +
                            medicalHistory.getHealthIssue());
                    params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT );
                    params.addRule(RelativeLayout.BELOW, currId);
                    currId = newId();
                    curr.setId(currId);
                    layout.addView(curr, params);
                    /**
                     * treatment
                     */
                    curr = new TextView(this);
                    curr.setText("Treatement: " +
                            medicalHistory.getTreatment());
                    params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT );
                    params.addRule(RelativeLayout.BELOW, currId);
                    currId = newId();
                    curr.setId(currId);
                    layout.addView(curr, params);
                    /**
                     * Notes
                     */
                    curr = new TextView(this);
                    curr.setText("Notes: " +
                            medicalHistory.getNotes());
                    params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT );
                    params.addRule(RelativeLayout.BELOW, currId);
                    currId = newId();
                    curr.setId(currId);
                    layout.addView(curr, params);

                }

            }
            else {
                Toast.makeText(this,"ERROR LOADING BIRD",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Get an unused id for programmatically adding ID's to textviews
     * @return An unused ID.
     */
    @SuppressWarnings("all")
    public int newId() {
        while(findViewById(++id)!=null){/** DO NOTHING**/};
        return id;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_bird, menu);
        return true;
    }

    public void openGetRelatives(View v)
    {
        if(this.currentBird!=null) {
            Intent intent = new Intent(this,ViewRelatives.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("bird", this.currentBird);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else {
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
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

    public void editBird(View v) {
        if(this.currentBird!=null) {
            Intent intent = new Intent(this,EditBird.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("bird", this.currentBird);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else {
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
    }

    public void retireBird(View view)
    {


        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to retire this bird?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        String birdId = intent.getStringExtra(SearchBird.EXTRA_MESSAGE);
                        currentBird = MainActivity.db.searchBirds(new Bird(birdId, "", "", "", "", "","")).get(0);
                        currentBird.setStatus(false);
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
