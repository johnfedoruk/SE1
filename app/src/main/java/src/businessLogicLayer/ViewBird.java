package src.businessLogicLayer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import net.javacrypt.se1.R;



import src.databaseLayer.DatabaseManager;
import src.databaseLayer.Bird;
import java.util.Calendar;


import src.databaseLayer.Bird;
import src.databaseLayer.DatabaseManager;
import android.widget.Adapter;
import android.widget.ListView;

/*
 * Created by Kaj Moroz
 *
 */
public class ViewBird extends AppCompatActivity {

    TextView birdBirthdate;
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
        Toast toast = Toast.makeText(getApplicationContext(), birdId, Toast.LENGTH_LONG);
        toast.show();

        /**
         * Append Bird's Info to xml elements
         */
        if(birdId != null)
        {
            Bird currentBird = db.searchBirds(birdId, "", "", "", "").get(0); //Bird IDs are unique
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
            }
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
