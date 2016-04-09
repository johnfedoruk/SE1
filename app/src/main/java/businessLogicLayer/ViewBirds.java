package businessLogicLayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import net.javacrypt.se1.R;
import java.util.ArrayList;
import domainObjects.Bird;
import databaseLayer.DatabaseManager;
import domainObjects.MedicalHistory;


@SuppressWarnings("all")
public class ViewBirds extends ActionBarActivity {
    private Bird currentBird = null;
    DatabaseManager db = MainActivity.db;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_birds);
        this.context = getApplicationContext();
        Bird bird;
        String Active;
        Intent intent = this.getIntent();
        Bundle b = intent.getExtras();
        Bird searchBird = (Bird)b.getSerializable("bird");
        ArrayList<Bird> query = db.searchBirds(searchBird);
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
        for(int i=0;i<query.size();i++) {
            Intent intentView = new Intent(this,ViewBird.class);
            Bundle bundle = new Bundle();
            bird = query.get(i);
            bundle.putSerializable("bird",bird);
            intentView.putExtras(bundle);
            adapt.setIntent(intentView);
            listView.setAdapter(adapt);
        }
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
        if (id == R.id.quit_or_logout)
        {
            Intent intent = new Intent(this, QuitScreen.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
