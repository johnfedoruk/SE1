package src.businessLogicLayer;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by johnny on 2/21/2016.
 */
public class SelectQuery implements View.OnClickListener {
    private final static String EXTRA_MESSAGE = "net.javacrypt.se1.MESSAGE";
    private Context context;
    private Intent intent;
    private String param;
    public SelectQuery(Context context,Intent intent,String param) {
        this.context = context;
        this.intent = intent;
        this.param = param;
    }
    @Override
    public void onClick(View v) {
        this.intent.putExtra(EXTRA_MESSAGE, param);
        this.context.startActivity(intent);
    }
}
