package src.businessLogicLayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.javacrypt.se1.R;

public class AddBirdSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bird_success);
    }

    public void BackToMenu(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}


