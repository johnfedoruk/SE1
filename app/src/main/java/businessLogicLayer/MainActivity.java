package businessLogicLayer;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.javacrypt.se1.R;

import databaseLayer.DatabaseHelper;
import databaseLayer.DatabaseManager;


@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {
    public static DatabaseManager db;
    public DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.dbHelper = new DatabaseHelper(getApplicationContext());
        this.db = new DatabaseManager(new DatabaseHelper(getApplicationContext()));
        //this.db.switchDatabases();
        //this.db.generateDatabase(dbHelper);
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


    /**
     * Allows the user to add a bird
     * @param view The View
     */
    public void openAddBird(View view) {
        Intent intent = new Intent(this,AddBird.class);
        startActivity(intent);
    }

    /**
     * Allows the user to add a bird
     * @param view The View
     */
    public void openSearchBird(View view) {
        Intent intent = new Intent(this,SearchBird.class);
        startActivity(intent);
    }

    /**
     * Allows the user to open an experiment
     * @param view The View
     */
    public void openAddExperiment(View view) {
        Intent intent = new Intent(this,AddExperiment.class);
        startActivity(intent);
    }

    /**
     * Allows the user to search for an experiment
     * @param view The View
     */
    public void openSearchExperiment(View view) {
        Intent intent = new Intent(this,SearchExperiment.class);
        startActivity(intent);
    }
   
}
