package src.businessLogicLayer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import net.javacrypt.se1.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SearchBird extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "net.javacrypt.se1.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bird);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_bird, menu);
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
    public void openViewBirds(View view) {
        Intent intent = new Intent(this,ViewBirds.class);
        String birdId = ((EditText)findViewById(R.id.legBandId)).getText().toString();
        String birdName = ((EditText)findViewById(R.id.birdName)).getText().toString();
        String birdSex = "";
        if(((CheckBox)findViewById(R.id.sexMale)).isChecked()==true
                &&((CheckBox)findViewById(R.id.sexMale)).isChecked()==true)
            birdSex = "both";
        else if(((CheckBox)findViewById(R.id.sexMale)).isChecked()==true)
            birdSex = "male";
        else if (((CheckBox)findViewById(R.id.sexMale)).isChecked()==true)
            birdSex = "female";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd mm yyyy");
        String birdBirth = "";
        if(((CheckBox)findViewById(R.id.searchBirth)).isChecked()==true) {
            DatePicker dp = (DatePicker)findViewById(R.id.birthDatePicker);
            birdBirth =
                    dateFormat.format(new Date(dp.getYear(),dp.getMonth(),dp.getDayOfMonth()));
        }
        String birdDeath = "";
        if(((CheckBox)findViewById(R.id.searchDeath)).isChecked()==true) {
            DatePicker dp = (DatePicker)findViewById(R.id.deathDatePicker);
            birdDeath =
                    dateFormat.format(new Date(dp.getYear(),dp.getMonth(),dp.getDayOfMonth()));
        }
        String[] searchParameters = {birdId,birdName,birdSex,birdBirth,birdDeath};
        intent.putExtra(EXTRA_MESSAGE,searchParameters);
        startActivity(intent);
    }
}
