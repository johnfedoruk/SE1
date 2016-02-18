package net.javacrypt.se1;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
public class ExpAddSuccess extends AppCompatActivity {
    /*============================JOSE============================*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_add_success);
    }

    public void openBackToMenu(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
/*============================JOSE============================*/