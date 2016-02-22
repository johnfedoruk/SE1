package src.businessLogicLayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import net.javacrypt.se1.R;
import java.util.ArrayList;

class ListAdapter extends ArrayAdapter<ListItem> {
    private ArrayList<ListItem> items;
    private Context ctx;
    private Intent intent;
    public ListAdapter(Context context, int textViewResourceId, ArrayList<ListItem> objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;
        this.ctx = context;
    }
    public void setIntent(Intent intent) {
        this.intent = intent;
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
            final TextView title = (TextView) v.findViewById(R.id.title);
            TextView info = (TextView) v.findViewById(R.id.info);
            if (title != null) {
                title.setText(m.titlePrefix+m.title);
            }
            if(info!=null){
                info.setText(m.infoPrefix+m.info);
            }
            RelativeLayout layout = (RelativeLayout)v.findViewById(R.id.item);
            final Intent intent = this.intent;
            final String param = m.title;
            layout.setOnClickListener(new SelectQuery(ctx,intent,param));
        }
        return v;
    }
}