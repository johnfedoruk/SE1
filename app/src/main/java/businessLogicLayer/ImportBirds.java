package businessLogicLayer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.javacrypt.se1.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import domainObjects.Bird;

/**
 * Created by Kaj on 4/8/2016.
 */
public class ImportBirds extends AppCompatActivity implements View.OnClickListener{
    static int id = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public int newId() {
        while(findViewById(++id)!=null){/** DO NOTHING**/};
        return id;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAddBird:
                runImport();
        }
    }

    private void runImport() {

        BirdParser parser = new BirdParser();
        ArrayList<String> errorList = parser.parseBirds();

        TextView curr;
        int currId;
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.medHistoryWrapper);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT );

        for(int i = 0; i < errorList.size(); i++) {
            /**
             * Use the errorList to list errors
             */
            currId = newId();
            curr = new TextView(this);
            curr.setId(currId);
            curr.setTypeface(null, Typeface.BOLD);
            curr.setText("Error Occurred in Line: " + errorList.get(i));
            curr.setTextSize(20);
            params.addRule(RelativeLayout.BELOW, R.id.btAddBird);
            params.setMargins(0, 20, 0, 0);
            layout.addView(curr, params);
        }

    }
}

