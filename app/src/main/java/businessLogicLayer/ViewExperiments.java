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

import databaseLayer.DatabaseManager;
import domainObjects.Experiment;


@SuppressWarnings("all")
public class ViewExperiments extends ActionBarActivity {
    private DatabaseManager db = MainActivity.db;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_experiments);
        this.context = getApplicationContext();
        String Active;
        Experiment experiment;
        Intent intent = this.getIntent();
        Bundle b = intent.getExtras();
        Experiment searchExperiment = (Experiment)b.getSerializable("experiment");
        ArrayList<Experiment> query = db.searchExperiments(searchExperiment);
        ListView listView = (ListView)this.findViewById(R.id.listView);
        ArrayList<ListItem> items = new ArrayList<>();
        for(int i=0;i<query.size();i++) {
            experiment = query.get(i);
            if(experiment.getStatus()==true){Active = "active";}
            else{Active="inactive";}
            items.add(new ListItem("Title: ",experiment.getStudyTitle(),"Type: ",
                    experiment.getStudyType(),"Status: ",Active));
        }
        ListAdapter adapt = new ListAdapter(this, R.layout.item, items);
        adapt.setIntent(new Intent(this,ViewExperiment.class));
        listView.setAdapter(adapt);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_experiments, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
