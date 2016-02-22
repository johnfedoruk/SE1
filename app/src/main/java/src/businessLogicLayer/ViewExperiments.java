package src.businessLogicLayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import net.javacrypt.se1.R;

import java.util.ArrayList;

import src.databaseLayer.DatabaseManager;
import src.databaseLayer.Experiment;


public class ViewExperiments extends ActionBarActivity {
    private DatabaseManager db = MainActivity.db;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_experiments);
        this.context = getApplicationContext();
        Experiment experiment;
        Intent intent = getIntent();
        String[] searchInfo = intent.getStringArrayExtra(SearchExperiment.EXTRA_MESSAGE);

        String StudyTitle = searchInfo[0];
        String StudyType = searchInfo[1];
        String GroupWithinExperiment = searchInfo[2];
        String StartDate = searchInfo[3];
        String EndDate = searchInfo[4];
        ArrayList<Experiment> query = db.searchExperiments(StudyTitle,StudyType,
                GroupWithinExperiment,StartDate,EndDate);
        ListView listView = (ListView)this.findViewById(R.id.listView);
        ArrayList<ListItem> items = new ArrayList<>();
        for(int i=0;i<query.size();i++) {
            experiment = query.get(i);
            items.add(new ListItem("Title: ",experiment.getStudyTitle(),"Type: ",
                    experiment.getStudyType()));
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
