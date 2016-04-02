package businessLogicLayer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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


@SuppressWarnings("all")
public class SearchExperiment extends ActionBarActivity implements View.OnFocusChangeListener,View.OnTouchListener{
    public final static String EXTRA_MESSAGE = "net.javacrypt.se1.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_experiment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_experiment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

        return super.onOptionsItemSelected(item);
    }

    /**
     * Allows the user to open an experiment
     * @param view The View
     */
    @SuppressWarnings("all")
    public void openViewExperiment(View view) {
        Intent intent = new Intent(this,ViewExperiments.class);
        String StudyTitle = ((EditText)findViewById(R.id.experimentTitle)).getText().toString();
        String StudyType = ((EditText)findViewById(R.id.experimentType)).getText().toString();
        String GroupWithinExperiment = ((EditText)findViewById(R.id.experimentGroup)).getText()
                .toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String StartDate = "";
        if(((CheckBox)findViewById(R.id.searchStart)).isChecked()) {
            DatePicker dp = (DatePicker)findViewById(R.id.startDatePicker);
            StartDate =
                    dateFormat.format(new Date(dp.getYear()-1900,dp.getMonth(),dp.getDayOfMonth()));
        }
        String EndDate = "";
        if(((CheckBox)findViewById(R.id.searchEnd)).isChecked()) {
            DatePicker dp = (DatePicker)findViewById(R.id.endDatePicker);
            EndDate =
                    dateFormat.format(new Date(dp.getYear()-1900,dp.getMonth(),dp.getDayOfMonth()));
        }
        String[] params = {StudyTitle,StudyType,
                GroupWithinExperiment,StartDate,EndDate};
        intent.putExtra(EXTRA_MESSAGE,params);
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
