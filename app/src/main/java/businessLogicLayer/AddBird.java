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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import databaseLayer.Bird;


/**
 *
 */
public class AddBird extends AppCompatActivity implements View.OnClickListener{

    //EditText txtLegBandId,txtName,txtExperiment,txtBirthDate,txtDeathDate,txtSex;

    public static TextView txtAddMedicalHistory;
    public static ImageView imgAddMedicalHistory;

    /**
     *
     * @param savedInstanceState
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
        txtAddMedicalHistory= (TextView) findViewById(R.id.txtAddMedicalHistory);
        AddBird.imgAddMedicalHistory = (ImageView) findViewById(R.id.imgAddMedicalHistory);
        Button btAddBird = (Button) findViewById(R.id.btAddBird);
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
                    deathdate.setTime(sdf.parse(txtDeathDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String sex = "";
                try {
                    sex = radioSexId.getText().toString();
                }
                catch(NullPointerException e){}
                Bird b = new Bird(id,name,exp,birthdate,deathdate,sex);
                MainActivity.db.addBird(b);

                ProgressDialog progressDialog = new ProgressDialog(AddBird.this);
                progressDialog.setTitle("Adding Bird");
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                /*Go to bird page*/
                Intent myIntent = new Intent(AddBird.this,AddBirdSuccess.class);
                startActivity(myIntent);

            }
        });

    }


    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_bird, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
   /* public void openAddBirdSuccess(View view) {
        Intent intent = new Intent(this, AddBirdSuccess.class);
        startActivity(intent);
    }*/

    /**
     *
     */
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

    /**
     *
     * @param view
     */
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

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    /**
     *
     * @param view
     */
    public void openAddMedicalHistory(View view) {

        Intent intent = new Intent(this,AddMedicalHistory.class);
        startActivity(intent);
    }
}