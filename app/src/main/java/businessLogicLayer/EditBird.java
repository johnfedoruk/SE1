package businessLogicLayer;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import net.javacrypt.se1.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import domainObjects.Bird;
import domainObjects.MedicalHistory;


/**
 *
 */
@SuppressWarnings("all")
public class EditBird extends AppCompatActivity implements View.OnClickListener{

    //EditText txtLegBandId,txtName,txtExperiment,txtBirthDate,txtDeathDate,txtSex;

    public static TextView txtAddMedicalHistory;
    public static ImageView imgAddMedicalHistory;
    public static MedicalHistory retrieveMedicalHistory;
    public static Bird currentBird = null;
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
        setContentView(R.layout.activity_edit_bird);

        /*
        *Listener for the AddBird button
        *
         */
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        this.currentBird = (Bird)bundle.getSerializable("bird");
        if(this.currentBird!=null) {
            TextView textView;
            EditText editText;
            RadioButton radioButton;
            String str = "dd-MM-yyyy";
            // autocomplete id
            textView = (TextView) findViewById(R.id.txtLegBandId);
            textView.setText(this.currentBird.getId());
            // autocomplete name
            textView = (TextView) findViewById(R.id.txtBirdName);
            textView.setText(this.currentBird.getName());
            // autocomplete experiment
            textView = (TextView) findViewById(R.id.txtExperiment);
            textView.setText(this.currentBird.getExperiment());
            // autocomplete birthDate
            editText = (EditText) findViewById(R.id.txtBirthDate);
            editText.setText(this.currentBird.getDateString(this.currentBird.getBirthDate(),str));
            // autocomplete deathDate
            editText = (EditText) findViewById(R.id.txtDeathDate);
            editText.setText(this.currentBird.getDateString(this.currentBird.getDeathDate(),str));
            // autocomplete sex
            if(this.currentBird.getSex().toLowerCase().equals("male"))
                radioButton = (RadioButton)findViewById(R.id.radioMale);
            else if(this.currentBird.getSex().toLowerCase().equals("female"))
                radioButton = (RadioButton)findViewById(R.id.radioFemale);
            else
                radioButton = null;
            if(radioButton!=null)
                radioButton.setChecked(true);
            // autocomplete medical history
            AddMedicalHistory.addHistory = this.currentBird.getMedicalHistory();

        }


        txtAddMedicalHistory = (TextView) findViewById(R.id.txtAddMedicalHistory);
        AddBird.imgAddMedicalHistory = (ImageView) findViewById(R.id.imgAddMedicalHistory);
        Button btAddBird = (Button) findViewById(R.id.btAddBird);
        retrieveMedicalHistory = AddMedicalHistory.addHistory;
        btAddBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Retrieve all the info from the textfields
                 */


                EditText txtLegBandId = (EditText) findViewById(R.id.txtLegBandId),
                        txtName = (EditText) findViewById(R.id.txtBirdName),
                        txtExperiment = (EditText) findViewById(R.id.txtExperiment),
                        txtBirthDate = (EditText) findViewById(R.id.txtBirthDate),
                        txtDeathDate = (EditText) findViewById(R.id.txtDeathDate);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                RadioGroup radioSex = (RadioGroup)findViewById(R.id.radioSex);
                int selectedId = radioSex.getCheckedRadioButtonId();
                RadioButton radioSexId = (RadioButton) findViewById(selectedId);


                String id = txtLegBandId.getText().toString();
                String name = txtName.getText().toString();
                String exp = txtExperiment.getText().toString();
                Calendar birthdate = Calendar.getInstance();
                Calendar deathdate = Calendar.getInstance();
                try {
                    birthdate.setTime(sdf.parse(txtBirthDate.getText().toString()));
                } catch (ParseException e) {
                    birthdate = null;
                    e.printStackTrace();
                }
                try {
                    deathdate.setTime(sdf.parse(txtDeathDate.getText().toString()));
                }
                catch(ParseException e) {
                    deathdate = null;
                }
                String sex = "";
                try {
                    sex = radioSexId.getText().toString();
                }
                catch(NullPointerException e){
                    return;
                }

                Bird b = new Bird(id,name,exp,birthdate,deathdate,sex,retrieveMedicalHistory,true);

             
                MainActivity.db.removeBird(EditBird.currentBird.getId());

                MainActivity.db.addBird(b);

                ProgressDialog progressDialog = new ProgressDialog(EditBird.this);
                progressDialog.setTitle("Editing Bird");
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                /*Go to bird page*/
                Intent myIntent = new Intent(EditBird.this,EditBirdSuccess.class);
                startActivity(myIntent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_bird, menu);
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

    @Override
    public void onClick(View v) {

    }

    public void openAddMedicalHistory(View view) {

        Intent intent = new Intent(this,AddMedicalHistory.class);
        startActivity(intent);
    }
}
