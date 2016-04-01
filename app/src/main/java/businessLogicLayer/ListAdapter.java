package businessLogicLayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
    private ArrayList<Intent> intents;
    public ListAdapter(Context context, int textViewResourceId, ArrayList<ListItem> objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;
        this.ctx = context;
        this.intents = new ArrayList<>();
    }
    public void setIntent(Intent intent) {
        this.intents.add(intent);
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
            TextView status = (TextView) v.findViewById(R.id.status);
            TextView statusinfo = (TextView) v.findViewById(R.id.statusinfo);

            if (title != null) {
                title.setText(m.titlePrefix+m.title);
            }
            if(info!=null){
                info.setText(m.infoPrefix+m.info);
            }
            if(status!=null){
               status.setText(m.status);
            }
            if(statusinfo!=null){
                statusinfo.setText(m.statusinfo);
                String statuscolor = statusinfo.getText().toString();
                if(statuscolor.equals("active")){statusinfo.setTextColor(Color.parseColor("#00cc00"));}
                else if(statuscolor.equals("inactive")){statusinfo.setTextColor(Color.parseColor("#cc0000"));}
            }

            RelativeLayout layout = (RelativeLayout)v.findViewById(R.id.item);
            final Intent intent = this.intents.get(position);
            final String param = m.title;
            layout.setOnClickListener(new SelectQuery(ctx,intent,param));
        }
        return v;
    }
}