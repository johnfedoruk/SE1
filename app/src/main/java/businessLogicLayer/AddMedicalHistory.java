package businessLogicLayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.javacrypt.se1.R;

public class AddMedicalHistory extends AppCompatActivity implements View.OnClickListener {

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
        finish();
    }
}
