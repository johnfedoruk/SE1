package src.businessLogicLayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import net.javacrypt.se1.R;
import java.util.ArrayList;
import src.databaseLayer.Bird;
import src.databaseLayer.DatabaseManager;


public class ViewBirds extends ActionBarActivity {
    private DatabaseManager db = new DatabaseManager();
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_birds);

        /**
         *
         * Get the search info
         * has the form: {"birdID","birdName","birdSex","birdBirth","birdDeath"}
         */
        this.context = getApplicationContext();
        Bird bird;
        ArrayList<Bird> query = db.search();
        ListView listView = (ListView)this.findViewById(R.id.listView);
        ArrayList<ListItem> items = new ArrayList<ListItem>();
        for(int i=0;i<query.size();i++) {
            bird = query.get(i);
            items.add(new ListItem("ID: ",bird.getId(),"Name: ",bird.getName()));
        }
        ListAdapter adapt = new ListAdapter(this, R.layout.item, items);
        Intent intent = new Intent(this,ViewBird.class);
        adapt.setIntent(intent);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
