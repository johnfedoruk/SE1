package businessLogicLayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.io.Serializable;

import net.javacrypt.se1.R;

import java.util.ArrayList;

import databaseLayer.DatabaseManager;
import domainObjects.Bird;

/**
 * Created by pure__000 on 2016-03-13.
 */
public class ViewRelatives extends AppCompatActivity implements View.OnClickListener {

    DatabaseManager db = MainActivity.db;
    public Context context;

    public void onClick(View vew)
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = new Bundle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_relatives);
        this.context = getApplicationContext();
        Bird bird;
        Intent intent = getIntent();
        Bird birdExtra = (Bird)intent.getSerializableExtra("bird");
        String id = birdExtra.getId();
        String Active;
        ArrayList<Bird> query = db.generateRelatives(id);
        ListView listView = (ListView)this.findViewById(R.id.listView2);
        ArrayList<ListItem> items = new ArrayList<>();
        for(int i=0;i<query.size();i++) {
            bird = query.get(i);
            items.add(new ListItem("ID: ",bird.getId(),"Name: ",bird.getName()));
        }
        ListAdapter adapt = new ListAdapter(this, R.layout.item, items);
        Intent intentView = new Intent(this,ViewBird.class);
        ArrayList<String> birdVals = new ArrayList<>();
        if(query.size() > 0) {
            bird = query.get(0);
            birdVals.add(bird.getId());
            birdVals.add(bird.getName());
            birdVals.add(bird.getExperiment());
            if (bird.getBirthDate() != null) {
                birdVals.add(String.valueOf(bird.getBirthDate().getTimeInMillis()));
            } else {
                birdVals.add("");
            }
            if (bird.getDeathDate() != null) {
                birdVals.add(String.valueOf(bird.getDeathDate().getTimeInMillis()));
            } else {
                birdVals.add("");
            }
            birdVals.add(bird.getSex());
            if (bird.getStatus()) {
                birdVals.add("true");
            } else {
                birdVals.add("false");
            }
            birdVals.add(bird.getMedicalHistory().toString());

            intentView.putStringArrayListExtra("bird", birdVals);
        }
        adapt.setIntent(intentView);
        listView.setAdapter(adapt);
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
        if (id == R.id.quit_or_logout)
        {
            Intent intent = new Intent(this, QuitScreen.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
