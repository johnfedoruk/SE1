package src.businessLogicLayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.javacrypt.se1.R;

import java.util.Calendar;

import src.databaseLayer.Bird;
import src.databaseLayer.DatabaseManager;


public class MainActivity extends AppCompatActivity {
    public static DatabaseManager db;
    //Calendar birthDate = Calendar.getInstance();
    //Calendar deathDate = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       this.db = new DatabaseManager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


           }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    /**
     * Allows the user to add a bird
     * @param view
     */
    public void openAddBird(View view) {
        Intent intent = new Intent(this,AddBird.class);
        startActivity(intent);
    }

    /**
     * Allows the user to add a bird
     * @param view
     */
    public void openSearchBird(View view) {
        Intent intent = new Intent(this,SearchBird.class);
        startActivity(intent);
    }
    public void openViewBird(View view) {
        Intent intent = new Intent(this,ViewBird.class);
        startActivity(intent);
    }
    public void openAddExperiment(View view) {
        Intent intent = new Intent(this,AddExperiment.class);
        startActivity(intent);
    }
    public void openSearchExperiment(View view) {
        Intent intent = new Intent(this,SearchExperiment.class);
        startActivity(intent);
    }
    public void openViewExperiment(View view) {

        Intent intent = new Intent(this,ViewExperiment.class);
        startActivity(intent);
    }
   
}
