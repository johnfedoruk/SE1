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
        this.context = getApplicationContext();
        Bird bird;
        Intent intent = getIntent();
        String[] searchInfo = intent.getStringArrayExtra(SearchBird.EXTRA_MESSAGE);
        String id = searchInfo[0];
        String name = searchInfo[1];
        String sex = searchInfo[2];
        String birdDate = searchInfo[3];
        String deathDate = searchInfo[4];
        ArrayList<Bird> query = db.searchBirds(id,name,sex,birdDate,deathDate);
        ListView listView = (ListView)this.findViewById(R.id.listView);
        ArrayList<ListItem> items = new ArrayList<ListItem>();
        for(int i=0;i<query.size();i++) {
            bird = query.get(i);
            items.add(new ListItem("ID: ",bird.getId(),"Name: ",bird.getName()));
        }
        ListAdapter adapt = new ListAdapter(this, R.layout.item, items);
        adapt.setIntent(new Intent(this,ViewBird.class));
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
