package businessLogicLayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import net.javacrypt.se1.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import domainObjects.Bird;


public class SearchBird extends AppCompatActivity implements View.OnFocusChangeListener,View.OnTouchListener{
    public final static String EXTRA_MESSAGE = "net.javacrypt.se1.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bird);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_bird, menu);
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

    @SuppressWarnings("all")
    public void openViewBirds(View view) {
        String birdId = ((EditText)findViewById(R.id.legBandId)).getText().toString();
        String birdName = ((EditText)findViewById(R.id.birdName)).getText().toString();
        String birdSex = "";
        if(((CheckBox)findViewById(R.id.sexMale)).isChecked()&&
                !((CheckBox)findViewById(R.id.sexFemale)).isChecked())
            birdSex = "male";
        else if (((CheckBox)findViewById(R.id.sexFemale)).isChecked()&&
                !((CheckBox)findViewById(R.id.sexMale)).isChecked())
            birdSex = "female";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birdBirth = "";
        if(((CheckBox)findViewById(R.id.searchBirth)).isChecked()) {
            DatePicker dp = (DatePicker)findViewById(R.id.birthDatePicker);
            birdBirth =
                    dateFormat.format(new Date(dp.getYear()-1900,dp.getMonth(),dp.getDayOfMonth()));
        }
        String birdDeath = "";
        if(((CheckBox)findViewById(R.id.searchDeath)).isChecked()) {
            DatePicker dp = (DatePicker)findViewById(R.id.deathDatePicker);
            birdDeath =
                    dateFormat.format(new Date(dp.getYear()-1900,dp.getMonth(),dp.getDayOfMonth()));
        }

        Intent intent = new Intent(this,ViewBirds.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bird", new Bird(birdId,birdName,null,birdBirth,birdDeath,birdSex,null));
        intent.putExtras(bundle);
        startActivity(intent);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideSoftKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus){
            hideSoftKeyboard(this);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
