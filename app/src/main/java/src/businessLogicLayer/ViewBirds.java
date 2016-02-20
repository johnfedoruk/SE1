package src.businessLogicLayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.javacrypt.se1.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import src.databaseLayer.Bird;
import src.databaseLayer.DatabaseManager;

class ListItem {
    public String birdId;
    public String birdName;
    public ListItem(String birdId,String birdName) {
        this.birdId = birdId;
        this.birdName = birdName;
    }
}
class ListAdapter extends ArrayAdapter<ListItem> {
    private ArrayList<ListItem> items;
    private Context ctx;
    public ListAdapter(Context context, int textViewResourceId, ArrayList<ListItem> objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;
        this.ctx = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)
                    ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.item, null);
        }
        ListItem m = items.get(position);
        if (m != null) {
            final TextView birdId = (TextView) v.findViewById(R.id.birdID);
            TextView birdName = (TextView) v.findViewById(R.id.birdName);
            if (birdId != null) {
                birdId.setText(m.birdId);
            }
            if(birdName!=null){
                birdName.setText(m.birdName);
            }
            RelativeLayout layout = (RelativeLayout)v.findViewById(R.id.item);
            layout.setOnClickListener(new RelativeLayout.OnClickListener(){
                public void onClick(View v) {
                    Toast toast = Toast.makeText(v.getContext(),
                            "bird " + ((TextView)v.findViewById(R.id.birdID)).getText().toString(),
                            Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            //layout.setMinimumWidth(300);
            //layout.setMinimumHeight(400);
            //layout.setBackgroundColor(0xc1fff6);
            layout.setBackgroundColor(0x21000f);
        }
        return v;
    }
}

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
        Intent intent = getIntent();
        String[] searchInfo = intent.getStringArrayExtra(SearchBird.EXTRA_MESSAGE);
        TextView tv;
        Bird bird;
        ArrayList<Bird> query = db.search();
        ListView stk = (ListView)this.findViewById(R.id.listView);
        ArrayList<ListItem> items = new ArrayList<ListItem>();
        for(int i=0;i<query.size();i++) {
            bird = query.get(i);
            items.add(new ListItem("ID: "+bird.getId(),"Name: "+bird.getName()));
        }
        ListAdapter adapt = new ListAdapter(this, R.layout.item, items);
        stk.setAdapter(adapt);
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
