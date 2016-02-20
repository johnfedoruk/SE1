package src.businessLogicLayer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import net.javacrypt.se1.R;

import src.databaseLayer.DatabaseManager;
import src.databaseLayer.Bird;


public class ViewBird extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bird);

        /**
         *
         * Get the bird ID
         *
         */
        Intent intent = getIntent();
        String birdName = intent.getStringExtra(SearchBird.EXTRA_MESSAGE);
        //TODO: Remove hardcoded line when SearchBird passes a bird
        birdName = "bird1";
        Toast toast = Toast.makeText(getApplicationContext(), birdName, Toast.LENGTH_LONG);
        toast.show();

        /**
         * Append Bird's Info to xml elements
         */
        if(birdName != null)
        {
            Bird currentBird = DatabaseManager.findBird(birdName);
            if (currentBird != null)
            {
                TextView curr = (TextView) findViewById(R.id.birdName);
                curr.append(currentBird.getName());

                curr = (TextView) findViewById(R.id.birdID);
                curr.append(currentBird.getId());

                curr = (TextView) findViewById(R.id.birdBirthdate);
                curr.append(currentBird.getBirthDate().toString());

                curr = (TextView) findViewById(R.id.birdDeathdate);
                curr.append(currentBird.getDeathDate().toString());

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
