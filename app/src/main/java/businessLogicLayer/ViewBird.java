package businessLogicLayer;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaCodecList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import net.javacrypt.se1.R;



import databaseLayer.DatabaseManager;
import databaseLayer.Bird;
import databaseLayer.MedicalHistory;

/*
 * Created by Kaj Moroz
 *
 */
public class ViewBird extends AppCompatActivity {

    TextView birdBirthdate;
    static int id = 1;
    @Override
    /*
     * When onCreate is called, it requires a Bird's ID tag to be passed into ViewBird.
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bird);
        DatabaseManager db = MainActivity.db;
        /**
         *
         * Get the bird ID from the Database
         *
         */

        Intent intent = getIntent();
        String birdId = intent.getStringExtra(SearchBird.EXTRA_MESSAGE);


        /**
         * Append Bird's Info to xml elements
         */
        if(birdId != null)
        {
            Bird currentBird = db.searchBirds(new Bird(birdId, "", "", "", "", "")).get(0); //Bird IDs are unique
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
                    RelativeLayout layout = (RelativeLayout)findViewById(R.id.contentWrapper);
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
                    params.addRule(RelativeLayout.BELOW, R.id.birdSex);
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
}
