package net.javacrypt.se1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    public void openViewExperiment(View view) {
        Intent intent = new Intent(this,AddExperiment.class);
        startActivity(intent);
    }
    public void openDelete(View view) {
        Intent intent = new Intent(this,AddExperiment.class);
        startActivity(intent);
    }




}
