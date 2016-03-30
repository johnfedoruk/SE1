package businessLogicLayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import net.javacrypt.se1.R;
import java.util.ArrayList;
import domainObjects.Bird;
import databaseLayer.DatabaseManager;
import domainObjects.MedicalHistory;


@SuppressWarnings("all")
public class ViewBirds extends ActionBarActivity {
    DatabaseManager db = MainActivity.db;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_birds);
        this.context = getApplicationContext();
        Bird bird;
        Intent intent = getIntent();
        String[] searchInfo = intent.getStringArrayExtra(SearchBird.EXTRA_MESSAGE);
        String id = searchInfo[0];
        String name = searchInfo[1];
        String sex = searchInfo[2];
        String birdDate = searchInfo[3];
        String deathDate = searchInfo[4];
        String Active;
        ArrayList<Bird> query = db.searchBirds(new Bird(id,name,birdDate,deathDate,sex,null,""));
        ListView listView = (ListView)this.findViewById(R.id.listView);
        ArrayList<ListItem> items = new ArrayList<>();
        if(query.size()==0)
            return;
        for(int i=0;i<query.size();i++) {
            bird = query.get(i);
            if(bird.getStatus()==true){Active = "active";}
            else{Active="inactive";}
            items.add(new ListItem("ID: ",bird.getId(),"Name: ",bird.getName(),"Status: ", Active));
        }
        ListAdapter adapt = new ListAdapter(this, R.layout.item, items);
        Intent intentView = new Intent(this,ViewBird.class);
        Bundle bundle = new Bundle();
        ArrayList<String> birdVals = new ArrayList<>();
        bird = query.get(0);
        birdVals.add(bird.getId());
        birdVals.add(bird.getName());
        birdVals.add(bird.getExperiment());
        if(bird.getBirthDate() != null)
        {
            birdVals.add(String.valueOf(bird.getBirthDate().getTimeInMillis()));
        }
        else
        {
            birdVals.add("");
        }
        if(bird.getDeathDate()!= null)
        {
            birdVals.add(String.valueOf(bird.getDeathDate().getTimeInMillis()));
        }
        else {
            birdVals.add("");
        }
        birdVals.add(bird.getSex());
        if(bird.getStatus())
        {
            birdVals.add("true");
        }
        else
        {
            birdVals.add("false");
        }
        birdVals.add(bird.getMedicalHistory().toString());

        birdVals.add(bird.getMom());
        birdVals.add(bird.getDad());

        intentView.putStringArrayListExtra("bird", birdVals);

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
