package businessLogicLayer;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.javacrypt.se1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import databaseLayer.DatabaseManager;
import domainObjects.Experiment;

/*============================JOSE============================*/
public class AddExperiment extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener,View.OnFocusChangeListener {

    DatabaseManager db = MainActivity.db;
    Button btCreateExperiment;
    public static DateParser dateParser = new DateParser();
    EditText txtStudyTitle, txtStudyType, txtGroupWithinExperiment, txtStartDate, txtEndDate, txtExperimenters, txtNotes;
    databaseLayer.ExperimentLocalStore ExperimentLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = this.getCurrentFocus();
        setContentView(R.layout.activity_add_experiment);


        txtStudyTitle = ((EditText) findViewById(R.id.txtStudyTitle));
        txtStudyType = ((EditText) findViewById(R.id.txtStudyType));
        txtGroupWithinExperiment = ((EditText) findViewById(R.id.txtGroupWithinExperiment));
        txtStartDate = ((EditText) findViewById(R.id.txtStartDate));
        txtEndDate = ((EditText) findViewById(R.id.txtEndDate));
        txtExperimenters = ((EditText) findViewById(R.id.txtExperimenters));
        txtNotes = ((EditText) findViewById(R.id.txtNotes));

        txtStudyTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {InputValidation.hasText(txtStudyTitle);}
        });

        txtStartDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {InputValidation.isDate(txtStartDate, true);}
        });

        txtEndDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {InputValidation.isDate(txtEndDate, false);}
        });

        btCreateExperiment = (Button) findViewById(R.id.btCreateExperiment);
        btCreateExperiment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(checkValidation()) {
                    String title = txtStudyTitle.getText().toString();
                    String type = txtStudyType.getText().toString();
                    String group = txtGroupWithinExperiment.getText().toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar sDate = Calendar.getInstance();
                    Calendar eDate = Calendar.getInstance();
                    try {
                        sDate.setTime(sdf.parse(txtStartDate.getText().toString()));
                    }
                    catch(Exception e) {
                        sDate = null;
                    }
                    try {
                        eDate.setTime(sdf.parse(txtEndDate.getText().toString()));
                    }
                    catch(Exception e) {
                        eDate = null;
                    }
                    /*
                    String startDate = txtStartDate.getText().toString();
                    String endDate = txtEndDate.getText().toString();
                    Calendar sDate = dateParser.toCalendar(startDate);
                    Calendar eDate;
                    if(endDate.equals(""))
                    {
                        eDate = null;
                    }
                    else
                    {
                        eDate = dateParser.toCalendar(endDate);
                    }
                    */

                    String experimenters = txtExperimenters.getText().toString();
                    String notes = txtNotes.getText().toString();
                    boolean active = true;

                    Experiment exp = new Experiment(title, type, group, sDate, eDate, experimenters, notes, active);
                    db.addExperiment(exp);
                    startActivity(new Intent(AddExperiment.this, ExpAddSuccess.class));
                    ProgressDialog progressDialog = new ProgressDialog(AddExperiment.this);
                    progressDialog.setTitle("Adding Experiment");
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();
                    Intent myIntent = new Intent(AddExperiment.this, ExpAddSuccess.class);
                    startActivity(myIntent);
                }
                else
                {
                    Toast.makeText(AddExperiment.this, "Form contains an error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_experiment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }
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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onStart() {
        super.onStart();


        EditText txtStartDate = (EditText) findViewById(R.id.txtStartDate);
        EditText txtEndDate = (EditText) findViewById(R.id.txtEndDate);
        txtStartDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
        txtEndDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!InputValidation.hasText(txtStudyTitle)) ret = false;
        if (!InputValidation.isDate(txtStartDate, true)) ret = false;
        if (!InputValidation.isDate(txtEndDate, false)) ret = false;
        return ret;
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
/*============================JOSE============================*/
