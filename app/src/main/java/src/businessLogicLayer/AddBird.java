package src.businessLogicLayer;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import net.javacrypt.se1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import src.databaseLayer.Bird;
import src.databaseLayer.DatabaseManager;


public class AddBird extends ActionBarActivity implements View.OnClickListener{


    DatabaseManager db = new DatabaseManager();
    //EditText txtLegBandId,txtName,txtExperiment,txtBirthDate,txtDeathDate,txtSex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bird);

        Button btAddBird = (Button) findViewById(R.id.btAddBird);
        btAddBird.setOnClickListener(new View.OnClickListener() {
        //txtLegBandId = ((EditText) findViewById(R.id.txtLegBandId));

            @Override
            public void onClick(View v) {

                EditText txtLegBandId = (EditText) findViewById(R.id.txtLegBandId),
                        txtName = (EditText) findViewById(R.id.txtBirdName),
                        txtExperiment = (EditText) findViewById(R.id.txtExperiment),
                        txtBirthDate = (EditText) findViewById(R.id.txtBirthDate),
                        txtDeathDate = (EditText) findViewById(R.id.txtDeathDate),
                        txtSex = (EditText) findViewById(R.id.txtSex);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String id = txtLegBandId.getText().toString();
                String name = txtName.getText().toString();
                String exp = txtExperiment.getText().toString();
                Date birthdate = null;
                Date deathdate = null;
                try {
                    birthdate = ((Date)sdf.parse(txtBirthDate.getText().toString()));

                 deathdate = ((Date)(sdf.parse(txtDeathDate.getText().toString())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String sex =  txtSex.getText().toString();

                ProgressDialog progressDialog = new ProgressDialog(AddBird.this);
                progressDialog.setTitle("Adding Bird");
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                Intent myIntent = new Intent(AddBird.this,AddBirdSuccess.class);
                Bird b = new Bird(id,name,exp,birthdate,deathdate,sex);
                db.addBird(b);
                startActivity(myIntent);

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


    @Override
    public void onClick(View v) {

    }
}
