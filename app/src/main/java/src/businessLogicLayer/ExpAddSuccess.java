
package src.businessLogicLayer;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.javacrypt.se1.R;

public class ExpAddSuccess extends AppCompatActivity {
    /*============================JOSE============================*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_add_success);
    }

    public void openBackToMenu(View view) {
        finish();
    }
}
/*============================JOSE============================*/