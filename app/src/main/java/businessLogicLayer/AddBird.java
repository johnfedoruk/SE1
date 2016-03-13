package businessLogicLayer;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.javacrypt.se1.R;

import java.util.Calendar;


import domainObjects.Bird;
import domainObjects.MedicalHistory;


/**
 *
 */
@SuppressWarnings("all")
public class AddBird extends AppCompatActivity implements View.OnClickListener{

    //EditText txtLegBandId,txtName,txtExperiment,txtBirthDate,txtDeathDate,txtSex;

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

                    Calendar bDate = dateParser.toCalendar(birthdate);
                    Calendar dDate;
                    if(deathdate.equals(""))
                    {
                        dDate = null;
                    }
                    else
                    {
                        dDate = dateParser.toCalendar(deathdate);
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

    public void onStart() {
        super.onStart();

        EditText txtBirthDate = (EditText) findViewById(R.id.txtBirthDate);
        EditText txtDeathDate = (EditText) findViewById(R.id.txtDeathDate);
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
}
