package businessLogicLayer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.javacrypt.se1.R;

import java.util.Calendar;

import domainObjects.MedicalHistory;

public class AddMedicalHistory extends AppCompatActivity implements View.OnClickListener {
    public static MedicalHistory addHistory = new MedicalHistory();
    public static DateParser dateParser = new DateParser();
    private EditText txtDateOfReport ,
            txtHealthIssue ,
            txtTreatment,
            txtNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medical_history);
        txtDateOfReport = (EditText) findViewById(R.id.txtDateOfReport);
        txtHealthIssue = (EditText) findViewById(R.id.txtHealthIssue);
        txtTreatment = (EditText) findViewById(R.id.txtTreatment);
        txtNotes = (EditText) findViewById(R.id.txtNotes);
        txtDateOfReport.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {InputValidation.isDate(txtDateOfReport, true);}
        });

    }

    public void onStart() {
        super.onStart();

        EditText txtDateOfReport = (EditText) findViewById(R.id.txtDateOfReport);
        txtDateOfReport.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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


    public void openAddBird(View view) {


        if(checkValidation()) {
            AddBird.txtAddMedicalHistory.setText("Added");
            AddBird.imgAddMedicalHistory.setImageResource(R.drawable.ic_action_tick);
            String health = txtHealthIssue.getText().toString();
            String treat = txtTreatment.getText().toString();
            String note = txtNotes.getText().toString();
            String dateOfReport = txtDateOfReport.getText().toString();

            Calendar dOReport = dateParser.toCalendar(dateOfReport);

            addHistory.setDateOfReport(dOReport);
            addHistory.setHealthIssue(health);
            addHistory.setTreatment(treat);
            addHistory.setNotes(note);
            AddBird.retrieveMedicalHistory = addHistory;
            finish();
        }
        else
        {
            Toast.makeText(AddMedicalHistory.this, "Form contains an error", Toast.LENGTH_LONG).show();
        }
    }
    private boolean checkValidation() {
        boolean ret = true;

        if (!InputValidation.isDate(txtDateOfReport, true)) ret = false;

        return ret;
    }
}
