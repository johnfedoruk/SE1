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
    public static ListView listView = null;
    public static ListAdapter adapt = null;
    public static ArrayList<ListItem> items = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_experiments);
        this.context = getApplicationContext();
        populateList();
    }
    public void populateList()
    {
        String Active;
        Experiment experiment;
        Intent intent = this.getIntent();
        Bundle b = intent.getExtras();
        Experiment searchExperiment = (Experiment)b.getSerializable("experiment");
        ArrayList<Experiment> query = db.searchExperiments(searchExperiment);
        if(query==null||query.size()<1)
            return;
       this.listView = (ListView)this.findViewById(R.id.listView);
       this.items = new ArrayList<>();

        for(int i=0;i<query.size();i++) {
            experiment = query.get(i);
            if(experiment.getStatus()==true){Active = "active";}
            else{Active="inactive";}
            items.add(new ListItem("Title: ",experiment.getStudyTitle(),"Type: ",
                    experiment.getStudyType(),"Status: ",Active));
        }
        this.adapt = new ListAdapter(this, R.layout.item, items);
        for(int i=0;i<query.size();i++) {
            Intent intentView = new Intent(this,ViewExperiment.class);
            Bundle bundle = new Bundle();
            experiment = query.get(i);
            bundle.putSerializable("experiment",experiment);
            intentView.putExtras(bundle);
            adapt.setIntent(intentView);
            listView.setAdapter(adapt);
        }
    }
    @Override
    public void onRestart(){
        populateList();
        super.onRestart();

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

        return MenubarMenu.handleMenu(this, id);
    }
}
