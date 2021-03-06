package businessLogicLayer;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.javacrypt.se1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import domainObjects.Bird;
import domainObjects.MedicalHistory;


/**
 *
 */
@SuppressWarnings("all")
public class AddBird extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener,View.OnFocusChangeListener{

    public static TextView txtAddMedicalHistory;
    public static ImageView imgAddMedicalHistory;
    public static MedicalHistory retrieveMedicalHistory;
    public static DateParser dateParser = new DateParser();
    private EditText txtLegBandId, txtName, txtExperiment, txtBirthDate, txtDeathDate, txtMotherId, txtFatherId;
    private Button btAddBird;
    private  RadioButton radioSexId;
    private RadioGroup radioSex;

    /**
     *
     * @param savedInstanceState savedState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = this.getCurrentFocus();
        /**
         *
         * ALL CLASSES SHOULD USE THE DATABASE MANAGER THAT IS BUILT BY THE MAIN ACTIVITY
         *
         */
        setContentView(R.layout.activity_add_bird);
        /*
        *Listener for the AddBird button
        *
         */
        txtLegBandId = (EditText) findViewById(R.id.txtLegBandId);
        txtName = (EditText) findViewById(R.id.txtBirdName);
        txtExperiment = (EditText) findViewById(R.id.txtExperiment);
        txtBirthDate = (EditText) findViewById(R.id.txtBirthDate);
        txtDeathDate = (EditText) findViewById(R.id.txtDeathDate);
        txtFatherId = (EditText) findViewById(R.id.txtFatherId);
        txtMotherId = (EditText) findViewById(R.id.txtMotherId);
        txtAddMedicalHistory = (TextView) findViewById(R.id.txtAddMedicalHistory);

        AddBird.imgAddMedicalHistory = (ImageView) findViewById(R.id.imgAddMedicalHistory);

        retrieveMedicalHistory = AddMedicalHistory.addHistory;



        radioSex = (RadioGroup) findViewById(R.id.radioSex);

        //TextWatchers
        txtLegBandId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {InputValidation.isID(txtLegBandId, true);}
        });

        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {InputValidation.hasText(txtName);}
        });

        txtBirthDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {InputValidation.isDate(txtBirthDate, true);}
        });

        txtDeathDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {InputValidation.isDate(txtDeathDate, false);}
        });

        txtMotherId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {InputValidation.isID(txtMotherId, false);}
        });

        txtFatherId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {InputValidation.isID(txtFatherId, false);}
        });
        radioSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioSex.getCheckedRadioButtonId()<=0) {
                    RadioButton rd = (RadioButton) findViewById(R.id.radioFemale);
                    rd.setError("Please select a gender");
                }
            }
        });

        btAddBird = (Button) findViewById(R.id.btAddBird);
        btAddBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Retrieve all the info from the textfields
                 */
                if(checkValidation()) {
                    String id = txtLegBandId.getText().toString();
                    String name = txtName.getText().toString();
                    String exp = txtExperiment.getText().toString();
                    String birthdate = txtBirthDate.getText().toString();
                    String deathdate = txtDeathDate.getText().toString();
                    String motherId = txtMotherId.getText().toString();
                    String fatherId = txtFatherId.getText().toString();

                    int selectedId = radioSex.getCheckedRadioButtonId();
                    radioSexId = (RadioButton) findViewById(selectedId);
                    String sex = "";
                    try {
                        sex = radioSexId.getText().toString();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar bDate = Calendar.getInstance();
                    Calendar dDate = Calendar.getInstance();
                    try {
                        bDate.setTime(sdf.parse(birthdate));
                    }
                    catch(Exception e) {
                        bDate = null;
                    }
                    try {
                        dDate.setTime(sdf.parse(deathdate));
                    }
                    catch(Exception e) {
                        dDate = null;
                    }
                    boolean status = true;
                    Bird b = new Bird(id, name, exp, bDate, dDate, sex, retrieveMedicalHistory, status, motherId, fatherId);
                    MainActivity.db.addBird(b);

                    ProgressDialog progressDialog = new ProgressDialog(AddBird.this);
                    progressDialog.setTitle("Adding Bird");
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();

                /*Go to bird page*/
                    Intent myIntent = new Intent(AddBird.this, AddBirdSuccess.class);
                    startActivity(myIntent);
                }
                else
                {
                   Toast.makeText(AddBird.this, "Form contains an error", Toast.LENGTH_LONG).show();
                }



            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_bird, menu);
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

    public void onStart() {
        super.onStart();

        EditText txtBirthDate = (EditText) findViewById(R.id.txtBirthDate);
        EditText txtDeathDate = (EditText) findViewById(R.id.txtDeathDate);
        txtBirthDate.setInputType(InputType.TYPE_NULL);
        txtDeathDate.setInputType(InputType.TYPE_NULL);
        txtBirthDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
        txtDeathDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioMale:
                if (checked)
                    break;
            case R.id.radioFemale:
                if (checked)
                    break;
        }
    }

     public void openAddMedicalHistory(View view) {

        Intent intent = new Intent(this,AddMedicalHistory.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAddBird:
                if(checkValidation()) {

                }
                else
                { Toast.makeText(AddBird.this, "Form contains an error", Toast.LENGTH_LONG).show();}
        }
    }
    private boolean checkValidation() {
        boolean ret = true;

        if (!InputValidation.isID(txtLegBandId, true)) ret = false;
        if (!InputValidation.isDate(txtBirthDate, true)) ret = false;
        if (!InputValidation.isDate(txtDeathDate, false)) ret = false;
        if (!InputValidation.hasText(txtName)) ret = false;
        if (radioSex.getCheckedRadioButtonId()<=0){
            RadioButton rd = (RadioButton) findViewById(R.id.radioFemale);
            rd.setError("Please select a gender");
            ret = false;}

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
