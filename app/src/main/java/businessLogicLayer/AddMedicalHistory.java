package businessLogicLayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import net.javacrypt.se1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import databaseLayer.MedicalHistory;

public class AddMedicalHistory extends AppCompatActivity implements View.OnClickListener {
    public static MedicalHistory addHistory = new MedicalHistory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medical_history);

    }

    @Override
    public void onClick(View v) {

    }


    public void openAddBird(View view) {


        AddBird.txtAddMedicalHistory.setText("Added");

        AddBird.imgAddMedicalHistory.setImageResource(R.drawable.ic_action_tick);

        EditText txtDateOfReport = (EditText) findViewById(R.id.txtDateOfReport),
                txtHealthIssue = (EditText) findViewById(R.id.txtHealthIssue),
                txtTreatment = (EditText) findViewById(R.id.txtTreatment),
                txtNotes = (EditText) findViewById(R.id.txtNotes);

        Calendar date = Calendar.getInstance();
        String health = txtHealthIssue.getText().toString();
        String treat = txtTreatment.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String note = txtNotes.getText().toString();
        try {
            date.setTime(sdf.parse(txtDateOfReport.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        addHistory.setDateOfReport(date);
        addHistory.setHealthIssue(health);
        addHistory.setTreatment(treat);
        addHistory.setNotes(note);
        AddBird.retrieveMedicalHistory = addHistory;
        finish();
    }
}
