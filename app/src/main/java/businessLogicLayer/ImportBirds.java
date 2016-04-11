package businessLogicLayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.javacrypt.se1.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import domainObjects.Bird;

/**
 * Created by Kaj on 4/8/2016.
 */
public class ImportBirds extends AppCompatActivity implements View.OnClickListener{
    static int id = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         *
         * ALL CLASSES SHOULD USE THE DATABASE MANAGER THAT IS BUILT BY THE MAIN ACTIVITY
         *
         */
        setContentView(R.layout.activity_import_birds);

    }


    public int newId() {
        while(findViewById(++id)!=null){/** DO NOTHING**/};
        return id;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAddBird:
                runImport(v);
        }
    }

    public void runImport(View view) {

        BirdParser parser;
        ArrayList<String> errorList = new ArrayList<>();
        BufferedReader buffRead;
        InputStream input;

        input = getApplicationContext().getResources().openRawResource(R.raw.bird);
        buffRead = new BufferedReader(new InputStreamReader(input));
        parser = new BirdParser(buffRead);
        errorList = parser.parseBirds();


        TextView curr;
        int currId;
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.medHistoryWrapper);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT );

        for(int i = 0; i+1 < errorList.size(); i++) {
            /**
             * Use the errorList to list errors
             */
            currId = newId();
            curr = new TextView(this);
            curr.setId(currId);
            curr.setTypeface(null, Typeface.BOLD);
            curr.setText("Error Occurred in Line: " + errorList.get(i));
            curr.setTextSize(20);
            params.addRule(RelativeLayout.BELOW, R.id.btAddBird);
            params.setMargins(0, 20, 0, 0);
            layout.addView(curr, params);
        }

        currId = newId();
        curr = new TextView(this);
        curr.setId(currId);
        curr.setTypeface(null, Typeface.BOLD);
        curr.setText("Birds Imported");
        curr.setTextSize(20);
        params.addRule(RelativeLayout.BELOW, R.id.btAddBird);
        params.setMargins(0, 20, 0, 0);
        layout.addView(curr, params);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_import_bird, menu);
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

